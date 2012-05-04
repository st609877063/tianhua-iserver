package com.platform.service.impl;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.platform.domain.Operation;
import com.platform.service.OperationService;
@Service("operationService")
public class OperationServiceImpl implements OperationService{
	private PersistenceManager pm;
	private QueryManager qm;
	
	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}

	@SuppressWarnings("unchecked")
	public String getOperations(){
		List<Operation> list = qm.findByNamedQuery("getOperations");
		Iterator<Operation> it = list.iterator();
		JSONArray array = new JSONArray();
		JSONObject object = null;
		try {
			for(;it.hasNext();){
			Operation operation = it.next();
			object=new JSONObject();
			object.put("operationId", operation.getOperationId());
			object.put("text",operation.getName());
			object.put("id", operation.getSeq());
			object.put("parentId", operation.getParentId());
			object.put("leaf", operation.isLeaf());
			array.put(object);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(array.toString());
		return array.toString();
	}

	@SuppressWarnings("unchecked")
	public Operation getOperationById(String id){
		List<Operation> list = qm.findByNamedQuery("getOperationById", id);
		System.out.println("length: "+list.size());
		Operation operation = list.get(0);
		return operation;
	}
	
	@SuppressWarnings("unchecked")
	public Operation getOperationBySeq(int seq){
		List<Operation> list = qm.findByNamedQuery("getOperationBySeq", seq);
		System.out.println("length: "+list.size());
		Operation operation = list.get(0);
		return operation;
		
	}
	
	@SuppressWarnings("unchecked")
	public Operation getOperationByName(String name){
		List<Operation> list = qm.findByNamedQuery("getOperationByName", name);
		System.out.println("length: "+list.size());
		Operation operation = list.get(0);
		return operation;
	}
	
	public boolean saveOperation(Operation operation){
		pm.save(operation);
		return true;
	}
	
	public boolean updateOperation(Operation operation){
		pm.update(operation);
		return true;
	}
	
	public boolean removeOperation(Operation operation){
		pm.remove(operation);
		return true;
	}
	
	public boolean removeOperationById(Operation operation,String id){
		pm.remove(Operation.class, id);
		return true;
	}
	@SuppressWarnings("unchecked")
	public boolean removeAllOperations(){
		List<Operation> list = qm.findByNamedQuery("getOperations");
		Iterator<Operation> it = list.iterator();
		for(;it.hasNext();){
			Operation operation = it.next();
			pm.remove(operation);
		}
		return true;
		}
		
}
