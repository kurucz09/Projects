package com.ass1.business;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.cfg.Configuration;

import com.ass1.da.SessionDAO;
import com.ass1.da.SessionEntity;
import com.ass1.da.UserDAO;
import com.ass1.da.UserEntity;

public class SessionLogic {
	
	private SessionDAO sessionDAO;
	private UserDAO userDAO;
	private final long LIFETIME = 3600000; 
	
	public SessionLogic(){
		sessionDAO = new SessionDAO(new Configuration().configure().buildSessionFactory());
		userDAO = new UserDAO(new Configuration().configure().buildSessionFactory());
		
	}
	
	
	
	public void checkExpiredSessions(){
		List<SessionEntity> sessions = null;
		sessions = sessionDAO.getAllSessions();
		long date_current = System.currentTimeMillis();
		for(SessionEntity ses : sessions){
			long date_created = Long.parseLong(ses.getDateCreated().toString());
			if(date_current - date_created > LIFETIME){
				sessionDAO.deleteSession(ses.getId());
			}
		}
	}
	
	public void createToken(UserEntity userLogged, HttpServletRequest req, HttpServletResponse res){
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		
		Cookie cookie = new Cookie("token", String.valueOf(n));
		res.addCookie(cookie);
		
		SessionEntity se = new SessionEntity();
		se.setDateCreated(String.valueOf(System.currentTimeMillis()));
		se.setToken(String.valueOf(n));
		se.setId(-1);
		se.setUsername(userLogged.getUsername());
		sessionDAO.addSession(se);
	}
	

	public boolean checkToken(String token, String role) {
		List<SessionEntity> sessions = null;
		sessions = sessionDAO.getAllSessions();

		for(SessionEntity ses : sessions){
			if(ses.getToken().contentEquals(token)){
				String username = ses.getUsername();
				List<UserEntity> users = userDAO.getAllUsers();
				for(UserEntity user : users){
					if(user.getUsername().contentEquals(username)){
						if(user.getRole().contentEquals(role)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
