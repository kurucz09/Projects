using System;
using System.Collections.Generic;
using System.Linq;


namespace Evenimente.Repository
{
    public class CommentRepository
    {
        public void Add(Comment comment)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Comments.Add(comment);
                context.SaveChanges();
            }
        }

        public List<Comment> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var comments = context.Comments;
                if (!comments.Equals(null))
                    return comments.ToList();
            }
            throw new Exception("ERROR comments not found");
        }

        public List<Comment> FindByEventId(int eventId)
        {
            using (var context = new EvenimenteEntities())
            {
                var comments = context.Comments.Where(comment => comment.EventId == eventId).ToList();
                if (comments.Count>0)
                    return comments;
            }
            throw new Exception("ERROR comments not found");
        }

        public Comment FindById(int commentId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findComment = context.Comments.FirstOrDefault(comment => comment.Id == commentId);
                if (findComment != null)
                    return findComment;
            }
            throw new Exception("ERROR comment not found");
        }

        public void Update(Comment comment)
        {
            using (var context = new EvenimenteEntities())
            {
                var toUpdateComment = context.Comments.FirstOrDefault(commentObj => commentObj.Id == comment.Id);
                if (toUpdateComment == null) throw new Exception("ERROR comments not found"); ;
                toUpdateComment.Description = comment.Description;
                toUpdateComment.Date = comment.Date;
                context.SaveChanges();
            }
        }

        public void Delete(int commentId)
        {
            using (var context = new EvenimenteEntities())
            {
                var toDeleteComment = context.Comments.FirstOrDefault(comment => comment.Id == commentId);
                if (toDeleteComment == null) throw new Exception("ERROR comments not found"); ;
                context.Comments.Remove(toDeleteComment);
                context.SaveChanges();
            }
        }
    }
}
