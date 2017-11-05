using System;

namespace WebApplication.Security
{
    public class SecretKey
    {
        private readonly String _secretKey = "wirtek";

        public string GetSecretKey()
        {
            return this._secretKey;
        }
    }
}