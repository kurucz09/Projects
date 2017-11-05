using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;
using WebApplication.Security;

namespace WebApplication.Controllers
{
    public class EventController : ApiController
    {
        private readonly EventRepository _eventRepository;
        private readonly AuthToken _authToken;

        public EventController()
        {
            _eventRepository = new EventRepository();
            _authToken = new AuthToken();
        }
        [HttpPost]
        public IHttpActionResult Add([FromBody] Event @event)
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
            Event eventObj;
            _eventRepository.Add(eventObj = @event);
            return Ok(eventObj.Id);
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
            var events = _eventRepository.GetAll();
            var resultEvents = events.Select(@event => new Event
            {
                Id = @event.Id,
                Description = @event.Description,
                Name = @event.Name,
                Place = @event.Place,
                Hour = @event.Hour,
                BeginDate = @event.BeginDate,
                EndDate = @event.EndDate,
                MinDateBirth = @event.MinDateBirth,
                NumberOfTickets = @event.NumberOfTickets,
                Status = @event.Status,
                UserId = @event.UserId,
                CategoryId = @event.CategoryId,
                Price = @event.Price
            });
            return Ok(resultEvents);
        }
        [HttpGet]
        public IHttpActionResult GetEventByCategory(int id)
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
                var eveniments = _eventRepository.GetAllByCategory(id);
                var resultEvents = eveniments.Select(@event => new Event
                {
                    Id = @event.Id,
                    Description = @event.Description,
                    Name = @event.Name,
                    Place = @event.Place,
                    Hour = @event.Hour,
                    BeginDate = @event.BeginDate,
                    EndDate = @event.EndDate,
                    MinDateBirth = @event.MinDateBirth,
                    NumberOfTickets = @event.NumberOfTickets,
                    Status = @event.Status,
                    UserId = @event.UserId,
                    CategoryId = @event.CategoryId,
                    Price = @event.Price
                });
                return Ok(resultEvents);
            }
            catch (Exception exception)
            {
                return Ok(new { errorCode= 25, message= exception.Message } );
            }
        }

        [HttpGet]
        public IHttpActionResult GetAllMinAge(int id)
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
                var eveniments = _eventRepository.GetAllMinAge(id);
                var resultEvents = eveniments.Select(@event => new Event
                {
                    Id = @event.Id,
                    Description = @event.Description,
                    Name = @event.Name,
                    Place = @event.Place,
                    Hour = @event.Hour,
                    BeginDate = @event.BeginDate,
                    EndDate = @event.EndDate,
                    MinDateBirth = @event.MinDateBirth,
                    NumberOfTickets = @event.NumberOfTickets,
                    Status = @event.Status,
                    UserId = @event.UserId,
                    CategoryId = @event.CategoryId,
                    Price = @event.Price
                });
                return Ok(resultEvents);
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
                var @event = _eventRepository.FindById(id);
                var resultEvent =
                    new Event
                    {
                        Id = @event.Id,
                        Description = @event.Description,
                        Name = @event.Name,
                        Place = @event.Place,
                        Hour = @event.Hour,
                        BeginDate = @event.BeginDate,
                        EndDate = @event.EndDate,
                        MinDateBirth = @event.MinDateBirth,
                        NumberOfTickets = @event.NumberOfTickets,
                        Status = @event.Status,
                        UserId = @event.UserId,
                        CategoryId = @event.CategoryId,
                        Price = @event.Price
                    };
                return Ok(resultEvent);
            }
            catch (Exception exception)
            {
                return BadRequest(exception.Message);
            }
        }

        

        [HttpPut]
        public IHttpActionResult Update([FromBody] dynamic @event)
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
            _eventRepository.Update(@event);
            return Ok();
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
            _eventRepository.Delete(id);
            return Ok();
        }
    }
}