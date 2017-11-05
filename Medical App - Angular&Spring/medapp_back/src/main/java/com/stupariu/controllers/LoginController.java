package com.stupariu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.entity.LogRequest;
import com.stupariu.entity.Message;
import com.stupariu.service.LoginService;


@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message checkAccount(HttpServletResponse response, @RequestBody LogRequest logRequest) {
		Message msg = new Message();
		msg.setMsg(this.loginService.checkAccount(logRequest));
		if(msg.getMsg()=="error"){
			try {
				response.sendError(401,"login_failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	
	}
	
}
