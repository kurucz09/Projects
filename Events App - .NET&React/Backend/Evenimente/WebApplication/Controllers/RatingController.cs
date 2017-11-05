using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;

namespace WebApplication.Controllers
{
    public class RatingController : ApiController
    {
        private readonly RatingRepository _ratingRepository;

        public RatingController()
        {
            _ratingRepository = new RatingRepository();
        }

        [HttpPost]
        public void Add([FromBody] Rating rating)
        {
            _ratingRepository.Add(rating);
        }

        [HttpGet]
        public IHttpActionResult Getall()
        {
            var ratings = _ratingRepository.GetAll();
            var resultRatings = ratings.Select(rating => new Rating
            {
                Id = rating.Id,
                EventId = rating.EventId,
                UserId = rating.UserId,
                Points = rating.Points
            });
            return Ok(resultRatings);
        }

        [HttpGet]
        public IHttpActionResult FindByIdE(int id)
        {
            var ratings = _ratingRepository.FindByIdE(id);
            var resultRatings = ratings.Select(rating => new Rating
            {
                Id = rating.Id,
                EventId = rating.EventId,
                UserId = rating.UserId,
                Points = rating.Points
            });
            return Ok(resultRatings);
        }

        [HttpGet]
        public IHttpActionResult FindByIdEur(int id)
        {
            var rating = _ratingRepository.FindByIdEur(id);
            var resultRating = new Rating
            {
                Id = rating.Id,
                EventId = rating.EventId,
                UserId = rating.UserId,
                Points = rating.Points
            };
            return Ok(resultRating);
        }

        [HttpPost]
        public void Update([FromBody] Rating rating)
        {
            _ratingRepository.Update(rating);
        }

        [HttpDelete]
        public void Delete(int id)
        {
            _ratingRepository.Delete(id);
        }
    }
}
