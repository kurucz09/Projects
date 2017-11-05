package com.ass1.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass1.business.LoginLogic;
import com.ass1.business.SessionLogic;
import com.ass1.da.UserEntity;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginLogic loginLogic;
	private SessionLogic sessionLogic;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		loginLogic = new LoginLogic();
		sessionLogic = new SessionLogic();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		if (username.contentEquals("") || password.contentEquals("")) {
			out.println("You must fill in both username and password");
		} else {


			UserEntity userLogged = loginLogic.logIn(username, password);

			if (userLogged != null) {
				sessionLogic.createToken(userLogged, request, response);
				if (userLogged.getRole().contentEquals("regular")) {
					response.sendRedirect("Homepage");
				} else if (userLogged.getRole().contentEquals("admin")) {
					response.sendRedirect("AdminPage");
				}
			} else {
				out.println("User not found. Try again.");
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
