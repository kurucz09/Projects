using System.Collections.Generic;
using System.Linq;

namespace Evenimente.Repository
{
    public class TicketRepository 
    {

        public void Add(Ticket ticket)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Tickets.Add(ticket);
                context.SaveChanges();
            }
        }
       
        public List<Ticket> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var tickets = context.Tickets;
                if (!tickets.Equals(null))
                {
                    return tickets.ToList();
                }
            }
            return null;
        }
     
        public Ticket FindById(int ticketId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findTicket= context.Tickets.FirstOrDefault(ticket => ticket.Id == ticketId);
                if (findTicket != null)
                {
                    return findTicket;
                }
            }
            return null;
        }

        public void Update(Ticket ticket)
        {
            using (var context = new EvenimenteEntities())
            {
                var toUpdateTicket = context.Tickets.FirstOrDefault(ticketObj => ticketObj.Id == ticket.Id);
                if (toUpdateTicket == null) return;
                toUpdateTicket.BuyedTickets = ticket.BuyedTickets;
                toUpdateTicket.EventId = ticket.EventId;
                toUpdateTicket.UserId = ticket.UserId;
                toUpdateTicket.Status = ticket.Status;

                context.SaveChanges();
            }
        }
      
        public void Delete(int ticketId)
        {
            using (var context = new EvenimenteEntities())
            {
                var toDeleteTicket = context.Tickets.FirstOrDefault(ticket => ticket.Id == ticketId);
                if (toDeleteTicket == null) return;
                context.Tickets.Remove(toDeleteTicket);
                context.SaveChanges();
            }
        }


    }
}
