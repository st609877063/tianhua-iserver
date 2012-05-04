package com.platform.service;

import com.platform.domain.Operation;

public interface OperationService {
	
	public String getOperations();
	
	public Operation getOperationById(String id);
	
	public Operation getOperationBySeq(int seq);
	
	public Operation getOperationByName(String name);
	
	public boolean saveOperation(Operation operation);
	
	public boolean updateOperation(Operation operation);
	
	public boolean removeOperation(Operation operation);
	
	public boolean removeAllOperations();
	
}
