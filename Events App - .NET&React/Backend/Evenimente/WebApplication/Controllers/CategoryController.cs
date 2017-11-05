using System;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;
using WebApplication.Security;

namespace WebApplication.Controllers
{
    public class CategoryController : ApiController
    {
        private readonly CategoryRepository _categoryRepository;

        private readonly AuthToken _authToken;

        public CategoryController()
        {
            _categoryRepository = new CategoryRepository();
            _authToken = new AuthToken();
        }

        [HttpPost]
        public IHttpActionResult Add([FromBody] Category category)
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
            _categoryRepository.Add(category);
            return Ok();
        }

        [HttpGet]
        public IHttpActionResult GetAll()
        {
            var headers = Request.Headers;
            if (!headers.Contains("auth_token") && !headers.Contains("fb_auth") )
            {
                return Ok(new { errorCode = "66", message = "unauthorized" });
            }
            if (headers.Contains("auth_token")) {
                var token = headers.GetValues("auth_token").First();
                if (!_authToken.VerifyToken(token))
                {
                    return Ok(new { errorCode = "66", message = "unauthorized" });
                }
            }


            try
            {
                var categories = _categoryRepository.GetAll();
                var resultCategories =
                categories.Select(category => new Category
                {
                    Id = category.Id,
                    Name = category.Name,
                    PictureURL = category.PictureURL
                });
                return Ok(resultCategories);
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
                var category = _categoryRepository.FindById(id);
                var resultCategory =
                    new Category
                    {
                        Id = category.Id,
                        Name = category.Name,
                        PictureURL = category.PictureURL
                    };
                return Ok(resultCategory);
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
            
        }


        [HttpPut]
        public IHttpActionResult Update([FromBody] Category category)
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
                _categoryRepository.Update(category);
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
            _categoryRepository.Delete(id);
            return Ok();
        }

    }
}