using System;
using System.Collections.Generic;
using System.Linq;


namespace Evenimente.Repository
{
    public class PictureRepository
    {

        public void Add(Picture picture)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Pictures.Add(picture);
                context.SaveChanges();
            }
        }

        public List<Picture> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var pictures = context.Pictures;
                if (pictures.ToList().Count>1)
                {
                    return pictures.ToList();
                }
                throw new Exception("ERROR pictures not found");
            }
           
        }

        public Picture FindById(int pictureId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findPicture = context.Pictures.FirstOrDefault(picture => picture.Id == pictureId);
                if (findPicture != null)
                {
                    return findPicture;
                }
                throw new Exception("ERROR picture not found");
            }
           
        }
        public Picture FindByEventId(int eventId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findPicture = context.Pictures.FirstOrDefault(picture => picture.EventId == eventId);
                if (findPicture != null)
                {
                    return findPicture;
                }
                throw new Exception("ERROR picture not found");
            }
          
        }

        public void Update(Picture picture)
        {
            using (var context = new EvenimenteEntities())
            {
                var toUpdatePicture = context.Pictures.FirstOrDefault(pictureObj => pictureObj.Id == picture.Id);
                if (toUpdatePicture == null) throw new Exception("ERROR picture not found"); ;
                toUpdatePicture.PictureURL = picture.PictureURL;
                context.SaveChanges();
            }
        }
    
        public void Delete(int pictureId)
        {
            using (var context = new EvenimenteEntities())
            {
                var toDeletePicture = context.Pictures.FirstOrDefault(picture => picture.Id == pictureId);
                if (toDeletePicture == null) throw new Exception("ERROR picture not found"); ;
                context.Pictures.Remove(toDeletePicture);
                context.SaveChanges();
            }
        }

       
    }
}
