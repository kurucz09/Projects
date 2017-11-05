package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.LogDAO;
import com.stupariu.Entity.Log;

@Service
public class LogService {
	
	@Autowired
	@Qualifier("logSQL")
	private LogDAO logDAO;
	
	public Collection<Log> getLogsOfUser(String username) {
		return this.logDAO.getLogsOfUser(username );
	}

	public void insertLog(Log log) {
		this.logDAO.insertLog(log);
	}

	public Collection<Log> getLogsOfUserInterval(String username, String startDate, String endDate) {
		return this.logDAO.getLogsOfUserInterval(username,startDate,endDate);
	}

}
