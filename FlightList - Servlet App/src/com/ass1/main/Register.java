package com.ass1.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass1.business.LoginLogic;
import com.ass1.da.UserEntity;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginLogic loginLogic;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		UserEntity responseUser = null;
		PrintWriter out = response.getWriter();
		try {
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			if (username.contentEquals("") || password.contentEquals("")) {
				out.println("<p>You must fill both fields</p>");
			} else {
				UserEntity user = new UserEntity();
				user.setId(-1);
				user.setUsername(username);
				user.setPassword(password);
				user.setRole("regular");
				responseUser = loginLogic.registerUser(user);
			}
		} finally {
			if (responseUser != null) {
				response.sendRedirect("Login.html");
			} else {
				out.println("Some error occured due to the fact that JSP was used some centuries ago");
			}
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
