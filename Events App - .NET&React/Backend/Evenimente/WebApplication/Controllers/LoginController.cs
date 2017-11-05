using System;
using System.Diagnostics;
using System.Threading.Tasks;
using System.Web.Http;
using Evenimente.Repository;
using WebApplication.Security;

namespace WebApplication.Controllers
{

    public class LoginController : ApiController
    {
        private readonly UserRepository _userRepository;

        private AuthToken _authToken;

        public LoginController()
        {
            _userRepository = new UserRepository();

        }

    
        [HttpPost]
        public IHttpActionResult Login([FromBody] dynamic user)
        {

            try
            {
                var usrnm = user.username.ToString();
                var result = _userRepository.FindByUsername(usrnm);

                if ((result == null) || (!result.Password.Equals(user.password.ToString(), StringComparison.Ordinal)))
                   return Ok(new { errorCode = 10, message = "Incorrect password. Please provide the right password" });

                _authToken = new AuthToken();
                var token = _authToken.CreateJwt("EventsApp", result.Username,
                    result.RoleId.ToString(), 30000000);

                return Ok(new { errorCode = 0, message = token });
            }
            catch (Exception e)
            {
                return Ok(new {errorCode = 10, message = "Wrong credentials"});
            }


        }


    }
}
