using System.Linq;
using System.Web.Http;
using Evenimente;
using Evenimente.Repository;

namespace WebApplication.Controllers
{
    public class TicketController : ApiController
    {
        private readonly TicketRepository _ticketRepository;

        public TicketController()
        {
            _ticketRepository = new TicketRepository();
        }
        [HttpPost]
        public void Add([FromBody] Ticket ticket)
        {
            _ticketRepository.Add(ticket);
        }
        [HttpGet]
        public IHttpActionResult GetAll()
        {
            var tickets = _ticketRepository.GetAll();
            var resulTickets = tickets.Select(ticket => new Ticket
            {
                Id = ticket.Id,
                EventId = ticket.EventId,
                UserId = ticket.UserId,
                BuyedTickets = ticket.BuyedTickets,
                Status = ticket.Status
            });
            return Ok(resulTickets);
        }

        [HttpGet]
        public IHttpActionResult FindById(int id)
        {
            var ticket = _ticketRepository.FindById(id);
            var resulTicket = new Ticket
            {
                Id = ticket.Id,
                EventId = ticket.EventId,
                UserId = ticket.UserId,
                BuyedTickets = ticket.BuyedTickets,
                Status = ticket.Status
            };
            return Ok(resulTicket);
        }

      

        [HttpPut]
        public void Update([FromBody] Ticket ticket)
        {
            _ticketRepository.Update(ticket);
        }

        [HttpDelete]
        public void Delete(int id)
        {
            _ticketRepository.Delete(id);
        }
    }
}