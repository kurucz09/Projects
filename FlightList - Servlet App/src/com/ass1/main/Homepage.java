package com.ass1.main;

import java.io.IOException;
import java.util.Date;
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
 * Servlet implementation class Login
 */
@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightLogic flightLogic;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homepage() {
        super();
        flightLogic = new FlightLogic();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<FlightEntity> flightList = flightLogic.getAllFlights();
		if(request.getAttribute("localindex") != null){
			int index = Integer.valueOf(request.getAttribute("localindex").toString());
			index--;
			FlightEntity flight = flightList.get(index);
			Date dateArrival = flightLogic.getLocalTimeArrival(flight);
			Date dateDeparture = flightLogic.getLocalTimeDeparture(flight);
			request.setAttribute("dateArrival", String.valueOf(dateArrival));
			request.setAttribute("dateDeparture", dateDeparture.toString());
		}
		
		request.setAttribute("flightList", flightList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");  
		if (dispatcher != null){  
			dispatcher.forward(request, response);  		
		} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
