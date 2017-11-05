using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;

namespace WebApplication.Controllers
{
    public class CommentController : ApiController
    {
        private readonly CommentRepository _commentRepository;

        public CommentController()
        {
            _commentRepository = new CommentRepository();
        }

        [HttpPost]
        public void Add([FromBody] Comment comment)
        {
            _commentRepository.Add(comment);
        }

        [HttpGet]
        public IHttpActionResult GetAll()
        {
            var comments = _commentRepository.GetAll();
            var resultComments = comments.Select(comment => new Comment
            {
                Id = comment.Id,
                EventId = comment.EventId,
                Description = comment.Description,
                Date = comment.Date,
                UserId = comment.UserId
            });
            return Ok(resultComments);
        }

        [HttpGet]
        public IHttpActionResult FindByIdComm(int id)
        {
            var findComment = _commentRepository.FindById(id);
            var resultComment = new Comment
            {
                Id = id,
                Description = findComment.Description,
                Date = findComment.Date,
                EventId = findComment.EventId,
                UserId = findComment.UserId
            };
            return Ok(resultComment);
        }

        [HttpGet]
        public IHttpActionResult FindByIdE(int id)
        {
            var findCategory = _commentRepository.FindByEventId(id);
            var resultComments = findCategory.Select(comment => new Comment
            {
                Id = comment.Id,
                EventId = comment.EventId,
                Description = comment.Description,
                Date = comment.Date,
                UserId = comment.UserId
            });
            return Ok(resultComments);
        }


        [HttpPost]
        public void Update([FromBody] Comment comment)
        {
            _commentRepository.Update(comment);
        }

        [HttpDelete]
        public void Delete(int id)
        {
            _commentRepository.Delete(id);
        }
    }
}