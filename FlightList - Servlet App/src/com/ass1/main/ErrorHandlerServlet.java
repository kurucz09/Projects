package com.ass1.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorHandlerServlet
 */
@WebServlet("/ErrorHandlerServlet")
public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
		String requestURI = (String) request.getAttribute("javax.servlet.error.request_uri");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Error handling</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h3><font color=red> Error page : </font></h3>");
		out.print("<p>Status Code :" + statusCode + " </p>");
		out.print("<p>Error Message : "+ errorMessage + "</p>");
		out.print("<p>Request URI: "+ requestURI + "</p>");
		out.print("<p>Servlet Name : "+ servletName + "</p>");
		
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
