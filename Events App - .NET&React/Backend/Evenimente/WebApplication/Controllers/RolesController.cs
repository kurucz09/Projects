using System;
using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;

namespace WebApplication.Controllers
{
    public class RolesController : ApiController
    {
        private readonly RolesRepository _rolesRepository;

        public RolesController()
        {
            _rolesRepository = new RolesRepository();
        }

        [HttpPost]
        public void Add([FromBody] Role role)
        {
            _rolesRepository.Add(role);
        }

        [HttpGet]
        public IHttpActionResult GetAll()
        {
            var roles = _rolesRepository.GetAll();
            var resultRoles = roles.Select(role => new {role.Id, Role = role.Name});
            return Ok(resultRoles);
        }

        [HttpGet]
        public IHttpActionResult FindById(int id)
        {
            try
            {
                var role = _rolesRepository.FindById(id);
                var resultRole = new Role {Id = role.Id, Name = role.Name};
                return Ok(resultRole);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet]
        public IHttpActionResult FindByName(string id)
        {
            try
            {
                var role = _rolesRepository.FindByName(id);
                return Ok(role);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPost]
        public IHttpActionResult Update([FromBody] Role role)
        {
            try
            {
                _rolesRepository.Update(role);
                return Ok();
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpDelete]
        public IHttpActionResult Delete(int id)
        {
            try
            {
                _rolesRepository.Delete(id);
                return Ok();
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}
