package com.stupariu.DAO;

import java.util.Collection;

import com.stupariu.Entity.Log;

public interface LogDAO {
	
	Collection<Log> getLogsOfUser(String username);
	
	void insertLog(Log log);

	Collection<Log> getLogsOfUserInterval(String username, String startDate, String endDate);
	
}
