using System;
using System.Collections.Generic;
using System.Linq;


namespace Evenimente.Repository
{
    public class EventRepository
    {

        public void Add(Event eveniment)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Events.Add(eveniment);
                context.SaveChanges();
            }
        }

        public List<Event> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var events = context.Events;
                if (events.ToList().Count>0)
                {
                    return events.ToList();
                }
                throw new Exception("ERROR events not found");
            }

        }
        public List<Event> GetAllByCategory(int categoryId)
        {
            using (new EvenimenteEntities())
            {
                var events = GetAll();
                var eveniments= events.Where(e => e.CategoryId == categoryId).ToList();
                if (eveniments.Capacity==0)
                    throw new Exception("ERROR events not found");
                return eveniments;
            }
        }

        public Event FindById(int eventId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findEveniment = context.Events.FirstOrDefault(@event => @event.Id == eventId);
                if (findEveniment != null)
                {
                    return findEveniment;
                }
                throw new Exception("ERROR event not found");
            }
     
        }
       
        public List<Event> GetAllMinAge(int minAge)
        {
            using (var context = new EvenimenteEntities())
            {
                var eventsList = (from eveniment in context.Events let dt = Convert.ToDateTime(eveniment.MinDateBirth) where VerifyAge(dt, minAge) select eveniment).ToList();
                if(eventsList.Count<1)
                    throw new Exception("No events found");
                return eventsList;
            }
        }

        internal bool VerifyAge(DateTime dateOfBirth, int minAge)
        {
            var now = DateTime.Today;
            var age = now.Year - dateOfBirth.Year;
            if (now.Month < dateOfBirth.Month || (now.Month == dateOfBirth.Month && now.Day < dateOfBirth.Day))
                age--;
            return age >= minAge;
        }
    
        public void Update(Event @event)
        {
            using (var context = new EvenimenteEntities())
            {
                var toUpdateEvent = context.Events.FirstOrDefault(evenim => evenim.Id == @event.Id);
                if (toUpdateEvent == null) throw new Exception("No events found"); ;
                toUpdateEvent.Name = @event.Name;
                toUpdateEvent.Description = @event.Description;
                toUpdateEvent.Place = @event.Place;
                toUpdateEvent.Hour = @event.Hour;
                toUpdateEvent.BeginDate = @event.BeginDate;
                toUpdateEvent.EndDate = @event.EndDate;
                toUpdateEvent.MinDateBirth = @event.MinDateBirth;
                toUpdateEvent.NumberOfTickets = @event.NumberOfTickets;
                toUpdateEvent.Status = @event.Status;
                toUpdateEvent.UserId = @event.UserId;
                toUpdateEvent.CategoryId = @event.CategoryId;
                context.SaveChanges();
            }
        }
      
        public void Delete(int eventId)
        {
            using (var context = new EvenimenteEntities())
            {
                var toDeletEveniment = context.Events.FirstOrDefault(@event => @event.Id == eventId);
                if (toDeletEveniment == null) return;
                context.Events.Remove(toDeletEveniment);
                context.SaveChanges();
            }
        }
    
       

    }
}