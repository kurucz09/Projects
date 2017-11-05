package com.stupariu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.dao.LoginDAO;
import com.stupariu.entity.Account;
import com.stupariu.entity.LogRequest;    

@Service
public class LoginService {


	@Autowired
	@Qualifier("loginSQL")
	private LoginDAO loginDAO;
	//Sample method to construct a JWT
	

	public String checkAccount(LogRequest logRequest) {
		AuthToken authToken = new AuthToken();
		Account returnMessage = this.loginDAO.checkAccount(logRequest.getUsername(),logRequest.getPassword());
		if(returnMessage!=null){
			String token = authToken.createJWT("MedicalApp",returnMessage.getUsername(),returnMessage.getAccountType().toString(),30000000);
			return token;
		} else {
			return "error";
		}
	}
	
	
}
