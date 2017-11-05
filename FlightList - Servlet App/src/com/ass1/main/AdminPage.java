package com.ass1.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass1.business.FlightLogic;
import com.ass1.da.FlightEntity;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightLogic flightLogic;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPage() {
		super();
		flightLogic = new FlightLogic();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<FlightEntity> flightList = flightLogic.getAllFlights();
		
		request.removeAttribute("errorMessage");
		if(request.getParameter("index")!=null){
			int index = Integer.valueOf(request.getParameter("index"));
			index--;
			request.setAttribute("selected", flightList.get(index));
		} else 
		if(request.getParameter("delete")!=null){
			int index = Integer.valueOf(request.getParameter("delete"));
			index--;
			flightLogic.removeFlight(flightList.get(index).getFlightNumber());
			flightList.remove(index);
		} else 
		if(request.getParameter("btnAddFlight")!=null){
			String flightNumber = request.getParameter("flightNumber");
			String airplaneType = request.getParameter("airplaneType");
			String departureCity = request.getParameter("departureCity");
			String departureDatetime = request.getParameter("departureDatetime");
			String arrivalCity = request.getParameter("arrivalCity");
			String arrivalDatetime = request.getParameter("arrivalDatetime");
			if(flightNumber.contentEquals("") || airplaneType.contentEquals("") || 
					departureCity.contentEquals("") || departureDatetime.contentEquals("")
					|| arrivalCity.contentEquals("") || arrivalDatetime.contentEquals("")){
				String message = "No input should be empty";
				request.setAttribute("errorMessage", message);
				
			} else {
				FlightEntity flight = new FlightEntity();
				flight.setFlightNumber(flightNumber);
				flight.setAirplaneType(airplaneType);
				flight.setDepartureCity(departureCity);
				flight.setDepartureDatetime(departureDatetime);
				flight.setArrivalCity(arrivalCity);
				flight.setArrivalDatetime(arrivalDatetime);
				FlightEntity newFlight = flightLogic.addFlight(flight);
				flightList.add(newFlight);
			}
		} else
		if(request.getParameter("btnUpdateFlight")!=null){
			String flightNumber = request.getParameter("flightNumber");
			String airplaneType = request.getParameter("airplaneType");
			String departureCity = request.getParameter("departureCity");
			String departureDatetime = request.getParameter("departureDatetime");
			String arrivalCity = request.getParameter("arrivalCity");
			String arrivalDatetime = request.getParameter("arrivalDatetime");
			if(flightNumber.contentEquals("") || airplaneType.contentEquals("") || 
					departureCity.contentEquals("") || departureDatetime.contentEquals("")
					|| arrivalCity.contentEquals("") || arrivalDatetime.contentEquals("")){
				String message = "No input should be empty";
				request.setAttribute("errorMessage", message);
				
			} else {
				FlightEntity flight = new FlightEntity();
				flight.setFlightNumber(flightNumber);
				flight.setAirplaneType(airplaneType);
				flight.setDepartureCity(departureCity);
				flight.setDepartureDatetime(departureDatetime);
				flight.setArrivalCity(arrivalCity);
				flight.setArrivalDatetime(arrivalDatetime);
				flightLogic.updateFlight(flight);
				FlightEntity flDelete = new FlightEntity();
				for(FlightEntity fl : flightList){
					if(fl.getFlightNumber().contentEquals(flight.getFlightNumber())){
						flDelete = fl;
						break;
					}
				}
				flightList.remove(flDelete);
				flightList.add(flight);
			}
		}
		
		request.setAttribute("flightList", flightList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminpage.jsp");  
		if (dispatcher != null){  
			dispatcher.forward(request, response);  		
		} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
