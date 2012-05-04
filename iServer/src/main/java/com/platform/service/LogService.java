package com.platform.service;

import java.util.List;

import com.platform.domain.Logs;

public interface LogService {
	
	public String getLogs();
	
	public List<Logs> getLogsList();
	
	public String getPageLogs(int start,int limit,String sortorder);
	
	public boolean saveLog(Logs log);
	
	public boolean deleteLog(Logs log);
}
