using System;
using System.Collections.Generic;
using System.Text;
using Evenimente.Repository;
using Jose;

namespace WebApplication.Security
{
    public class AuthToken
    {
        private readonly SecretKey _secretKey = null;

        public AuthToken()
        {
            _secretKey = new SecretKey();
        }

        public string CreateJwt(String id, String subject, String role, long ttlMillis)
        {
            if (id == null) throw new ArgumentNullException(nameof(id));
            if (subject == null) throw new ArgumentNullException(nameof(subject));
            if (role == null) throw new ArgumentNullException(nameof(role));
            var currentTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            var expiration = currentTime + ttlMillis;

            var payload = new Dictionary<string, object>()
            {
                {"iss", "evenimente"},
                {"sub", subject},
                {"iat", currentTime},
                {"exp", expiration},
                {"role", role}
            };

            var key = Encoding.ASCII.GetBytes(_secretKey.GetSecretKey());

            var token = Jose.JWT.Encode(payload, key, JwsAlgorithm.HS256);

            return token;
        }


        private static IDictionary<string, object> Decode(string token)
        {
            var payloadJson = Jose.JWT.Payload<IDictionary<string, object>>(token);
            return payloadJson;
        }

        public bool CheckRole(string token, RolesEnum role)
        {
            var rolesRepository = new RolesRepository();
            var id = rolesRepository.FindByName(role.ToString()).Id;
            var decodedToken = Decode(token);
            var tokenId = int.Parse(decodedToken["role"].ToString());
            return id == tokenId;
        }

        public bool VerifyToken(string token)
        {
            if (token == null) throw new ArgumentNullException(nameof(token));
            var key = Encoding.ASCII.GetBytes(_secretKey.GetSecretKey());

            try
            {
                Jose.JWT.Decode(token, key, JwsAlgorithm.HS256);
            }
            catch
            {
                return false;
            }
            return true;
        }

    }
}