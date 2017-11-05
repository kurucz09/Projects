package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Log;
import com.stupariu.Service.LogService;

@RestController
@RequestMapping("/reports")
@CrossOrigin
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Log> getLogsOfUser(@PathVariable("username") String username) {
		return this.logService.getLogsOfUser(username);
	}
	
	@RequestMapping(value = "/{username}/{startDate}/{endDate}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Log> getLogsOfUserInterval(@PathVariable("username") String username,
			@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
		return this.logService.getLogsOfUserInterval(username,startDate,endDate);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertLog(@RequestBody Log log) {
		this.logService.insertLog(log);
	}
	
}
