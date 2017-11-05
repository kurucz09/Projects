using System;
using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;
using WebApplication.Security;

namespace WebApplication.Controllers
{
    public class PictureController : ApiController
    {
        private readonly PictureRepository _pictureRepository;

        private readonly AuthToken _authToken;

        public PictureController()
        {
            _pictureRepository = new PictureRepository();
            _authToken = new AuthToken();
        }

        [HttpPost]
        public void Add([FromBody] Picture picture)
        {
            _pictureRepository.Add(picture);
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
                var pictures = _pictureRepository.GetAll();
                var resultPictures =
                    pictures.Select(picture => new Picture
                    {
                        Id = picture.Id,
                        EventId = picture.EventId,
                        PictureURL = picture.PictureURL
                    });
                return Ok(resultPictures);
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
            try
            {
                var picture = _pictureRepository.FindById(id);
                var resultPicture = new Picture
                {
                    Id = picture.Id,
                    EventId = picture.EventId,
                    PictureURL = picture.PictureURL
                };
                return Ok(resultPicture);
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult FindByEventId(int id)
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
                var picture = _pictureRepository.FindByEventId(id);
                var resultPicture = new Picture
                {
                    Id = picture.Id,
                    EventId = picture.EventId,
                    PictureURL = picture.PictureURL
                };
                return Ok(resultPicture);
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
        }

        [HttpPut]
        public IHttpActionResult Update([FromBody] Picture picture)
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
                _pictureRepository.Update(picture);
                return Ok();
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
        }

        [HttpDelete]
        public IHttpActionResult Delete(int id)
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
            _pictureRepository.Delete(id);
            return Ok();
        }
    }
}
