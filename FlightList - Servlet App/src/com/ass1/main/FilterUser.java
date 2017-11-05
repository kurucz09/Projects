package com.ass1.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass1.business.SessionLogic;

/**
 * Servlet Filter implementation class FilterUser
 */
@WebFilter("/FilterUser")
public class FilterUser implements Filter {
	private SessionLogic sessionLogic;

    /**
     * Default constructor. 
     */
    public FilterUser() {
        // TODO Auto-generated constructor stub
    	sessionLogic = new SessionLogic();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("DO method is called in " + this.getClass().getName());
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		sessionLogic.checkExpiredSessions();
		Cookie[] cookies = req.getCookies();
		if(cookies.length == 0){
			res.sendRedirect("Login.html");
			return;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cook = cookies[i];
			if (cook.getName().contentEquals("token")) {
				if (!sessionLogic.checkToken(cook.getValue(), "regular")) {
					res.sendRedirect("Login.html");
					return;
				}
				chain.doFilter(request, response);
				return;
			} 
		}
		res.sendRedirect("Login.html");
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Init method is called in " + this.getClass().getName());
	}

}
