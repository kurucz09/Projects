using System;
using System.Collections.Generic;
using System.Linq;
using Evenimente.Exceptions;


namespace Evenimente.Repository
{
    public class UserRepository
    {

        public void Add(User user)
        {

            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(r => r.Username == user.Username);
                if (users != null)
                {
                    throw new UserCreateException("This username already exists");
                }
                
                contextEntities.Users.Add(user);
                contextEntities.SaveChanges();
            }
        }

        public List<User> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var users = context.Users;
                if (users.ToList().Count>0)
                {
                    return users.ToList();
                }
            }
            throw new Exception("No user could be found");
        }
       
        public User FindById(int userId)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(user => user.Id== userId);
                if(users==null)
                    throw new Exception("User with this id could not be found");
                return users;
            }
        }

        public User FindByUsername(string username)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(userObj => userObj.Username == username);
                if (users == null)
                    throw new Exception("User with this username could not be found");
                return users;
            }
        }

        public void Update(User user)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(userObj => userObj.Username == user.Username);
                if (users == null)
                    throw new Exception("User with this username does not exist");
                users.Name = user.Name;
                users.Email = user.Email;
                if(user.Password != null && user.Password != "**********") { 
                    users.Password = user.Password;
                }
                users.RoleId = user.RoleId;
                users.Username = user.Username;
                users.DateBirth = user.DateBirth;
                users.PictureURL = user.PictureURL;
                contextEntities.SaveChanges();
            }
        }

        public void Delete(string username)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(user => user.Username ==username);
                if (users == null) throw new Exception("User with this username could not be found");
                contextEntities.Users.Remove(users);
                contextEntities.SaveChanges();
            }
        }

        public void Delete(int idUser)
        {
            using (var contextEntities = new EvenimenteEntities())
            {
                var users = contextEntities.Users.FirstOrDefault(user => user.Id == idUser);
                if (users == null) throw new Exception("User with this id could not be found");
                contextEntities.Users.Remove(users);
                contextEntities.SaveChanges();
            }
        }

    }
}
