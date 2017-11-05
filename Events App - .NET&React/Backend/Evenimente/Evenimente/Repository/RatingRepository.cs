using System;
using System.Collections.Generic;
using System.Linq;


namespace Evenimente.Repository
{
    public class RatingRepository
    {

        public void Add(Rating rating)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Ratings.Add(rating);
                context.SaveChanges();
            }
        }

        public List<Rating> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var ratings = context.Ratings;
                if (ratings.ToList().Count>0)
                {
                    return ratings.ToList();
                }
            }
            throw new Exception("ERROR ratings not found");
        }

        public List<Rating> FindByIdE(int eventId)
        {
            using (var context = new EvenimenteEntities())
            {
                var ratings = context.Ratings.Where(rating => rating.EventId == eventId).ToList();
                if (!ratings.Equals(null))
                    return ratings;
            }
            throw new Exception("ERROR rating not found"); ;
        }
     
        public Rating FindByIdEur(int ratingId)
        {
            using (var context = new EvenimenteEntities())
            {
                return context.Ratings.FirstOrDefault(rating => rating.Id == ratingId);
            }
        }
    
        public void Update(Rating rating)
        {
            using (var context = new EvenimenteEntities())
            {

                var toUpdateRating = context.Ratings.FirstOrDefault(ratingObj => ratingObj.Id == rating.Id);
                if (toUpdateRating == null) throw new Exception("ERROR rating not found"); ;
                toUpdateRating.Points = rating.Points;
                toUpdateRating.EventId = rating.EventId;
                toUpdateRating.UserId = rating.UserId;
                context.SaveChanges();
            }

        }
       
        public void Delete(int ratingId)
        {
            using (var context = new EvenimenteEntities())
            {
                var toDeleteRating = context.Ratings.FirstOrDefault(rating => rating.Id == ratingId);
                if (toDeleteRating == null) throw new Exception("ERROR rating not found"); ;
                context.Ratings.Remove(toDeleteRating);
                context.SaveChanges();
            }

        }
    }
}