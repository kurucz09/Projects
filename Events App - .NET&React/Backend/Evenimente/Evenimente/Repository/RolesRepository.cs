using System;
using System.Collections.Generic;
using System.Linq;

namespace Evenimente.Repository
{
    public class RolesRepository
    {
        public void Add(Role role)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                contextEntities.Roles.Add(role);
                contextEntities.SaveChanges();
            }
        }

        public List<Role> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var roles = context.Roles;
                if (roles.ToList().Count > 0)
                    return roles.ToList();
            }
            throw new Exception("ERROR roles not found");
        }

        public Role FindById(int roleId)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var findRole = contextEntities.Roles.FirstOrDefault(role => role.Id == roleId);
                if (findRole != null)
                    return findRole;
            }
            throw new Exception("ERROR role not found");
        }

        public Role FindByName(string name)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var findRole = contextEntities.Roles.FirstOrDefault(role => role.Name == name);
                if (findRole != null)
                    return findRole;
                throw new Exception("ERROR roles not found");
            }
        }

        public void Update(Role role)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var updateRole = contextEntities.Roles.FirstOrDefault(roleObj => roleObj.Id == role.Id);
                if (updateRole == null) throw new Exception("ERROR role not found");
                updateRole.Name = role.Name;
                contextEntities.SaveChanges();
            }
        }

        public void Delete(int roleId)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var deleteRole = contextEntities.Roles.FirstOrDefault(role => role.Id == roleId);
                if (deleteRole == null) throw new Exception("ERROR role not found");
                contextEntities.Roles.Remove(deleteRole);
                contextEntities.SaveChanges();
            }
        }
    }
}
