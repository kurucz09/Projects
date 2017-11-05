using System;
using System.Linq;
using System.Net;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;
using WebApplication.Security;

namespace WebApplication.Controllers
{
    public class UserController : ApiController
    {
        private readonly UserRepository _userRepository;
        private readonly AuthToken _authToken;

        public UserController()
        {
            _userRepository = new UserRepository();
            _authToken = new AuthToken();
        }
        // [Route("user/insert")]
        [HttpPost]
        public IHttpActionResult Add([FromBody] User user)
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            try
            {
                _userRepository.Add(user);
                return Ok();
            }
            catch (Exception exception)
            {
                return Content(HttpStatusCode.BadRequest, exception.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult GetAll()
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            try
            {
                var users = _userRepository.GetAll();
                var resultUsers = users.Select(user => new User
                {
                    Id = user.Id,
                    Name = user.Name,
                    Email = user.Email,
                    Username = user.Username,
                    DateBirth = user.DateBirth,
                    PictureURL = user.PictureURL

                });
                return Ok(resultUsers);
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult FindById(int id)
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            var user = _userRepository.FindById(id);
            if (user == null) return BadRequest();
            var resultUser = new User
            {
                Id = user.Id,
                Name = user.Name,
                Email = user.Email,
                Username = user.Username,
                DateBirth = user.DateBirth,
                PictureURL = user.PictureURL
            };
            return Ok(resultUser);
        }

        [HttpGet]
        public IHttpActionResult FindByUsername(string id)
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            var user = _userRepository.FindByUsername(id);
            var resultUser = new User
            {
                Id = user.Id,
                Name = user.Name,
                Email = user.Email,
                Password = "**********",
                RoleId = user.RoleId,
                Username = user.Username,
                DateBirth = user.DateBirth,
                PictureURL = user.PictureURL
            };
            return Ok(resultUser);
        }

        [HttpPost]
        public IHttpActionResult Update([FromBody] User user)
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            _userRepository.Update(user);
            return Ok();
        }

        [HttpDelete]
        public IHttpActionResult Delete(string id)
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth"))
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token"))
            {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }
            _userRepository.Delete(id);
            return Ok();
        }

      
    }
}