package com.platform.service.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.RowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.persistence.JdbcQueryManager;
import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.platform.domain.Logs;
import com.platform.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {
	private PersistenceManager pm;
	private QueryManager qm;
	private JdbcQueryManager jqm;

	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}

	public void setJdbcQueryManager(JdbcQueryManager jqm) {
		this.jqm = jqm;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getLogs() {
		List<Logs> list = qm.findByNamedQuery("getLogs");
		Iterator<Logs> it = list.iterator();
		JSONObject obj = new JSONObject();
		JSONArray jarray = new JSONArray();
		try {
			obj.put("total", list.size());
			obj.put("page", 1);
			for (; it.hasNext();) {
				Logs log = it.next();
				JSONObject object = new JSONObject();
				//JSONArray array = new JSONArray();
				//object.put("id", log.getLogId());//id
				//array.put(log.getLogId());
				//array.put(log.getType());
				//array.put(log.getUsername());
				//array.put(log.getMessage());
				//array.put(log.getCreateDate());
				//object.put("cell", array);//cell
				object.put("logId",log.getLogId());
				object.put("type",log.getType());
				object.put("username",log.getUsername());
				object.put("message",log.getMessage());
				object.put("createDate",log.getCreateDate());
				jarray.put(object);
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Logs> getLogsList() {
		List<Logs> list = qm.findByNamedQuery("getLogs");
		return list;
	}

	public String getPageLogs(int start, int limit,String sortorder) {
		String sql = "select * from logs order by CREATE_DATE "+sortorder+" limit "+start+","+limit; 
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		JSONArray jarray = new JSONArray();
		JSONObject obj = new JSONObject();
		int size = getLogsList().size();
		try {
			obj.put("total", size);
			obj.put("page", page);
			try {
				while (rs.next()) {
					JSONObject object = new JSONObject();
					//JSONArray array = new JSONArray();
					//object.put("id", rs.getString("LOG_ID"));//id
					//array.put(rs.getString("LOG_ID"));
					//array.put(rs.getString("TYPE"));
					//array.put(rs.getString("USERNAME"));
					//array.put(rs.getString("MESSAGE"));
					//array.put(rs.getString("CREATE_DATE"));
					object.put("logId",rs.getString("LOG_ID"));
					object.put("type",rs.getString("TYPE"));
					object.put("username",rs.getString("USERNAME"));
					object.put("message",rs.getString("MESSAGE"));
					object.put("createDate",rs.getString("CREATE_DATE"));
					//object.put("cell", array);//cell
					jarray.put(object);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}

	public boolean saveLog(Logs log) {
		pm.save(log);
		return true;
	}

	public boolean deleteLog(Logs log) {
		pm.remove(log);
		return true;

	}
}
