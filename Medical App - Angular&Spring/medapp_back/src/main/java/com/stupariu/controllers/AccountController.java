package com.stupariu.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.entity.Account;
import com.stupariu.enums.AccountType;
import com.stupariu.service.AccountsService;
import com.stupariu.service.AuthToken;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountsService accountsService;

	private Boolean checkRole(HttpServletRequest request) {
		String token = request.getHeader("token");
		AuthToken authToken = new AuthToken();
		String role = authToken.getRole(token);

		if (role.contentEquals(AccountType.Administrator.toString())) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Account> getAllAccounts(HttpServletRequest request, HttpServletResponse response) {
		if (checkRole(request)) {
			return this.accountsService.getAllAccounts();
		} else {
			try {
				response.sendError(401, "error_role_not_given_GET");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody Account account) {
		if (checkRole(request)) {
			this.accountsService.updateAccount(account);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAccount(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("username") String username) {
		if (checkRole(request)) {
			this.accountsService.deleteAccount(username);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody Account account) {
		if (checkRole(request)) {
			this.accountsService.insertAccount(account);
		} else {
			try {
				response.sendError(401, "error_role_not_given");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
