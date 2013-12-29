/**
 * TwitterBeanProcessor.java
 * description: 
 * microblog
 * author: mayq
 * 2009-10-28
 */
package com.people.dptwb.manager;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.StringUtils;


/**
 * 
 * Description: 
 * @author mayq
 * 2009-10-28
 */
public class TwitterBeanProcessor extends BeanProcessor {

	/* (non-Javadoc)
	 * @see org.apache.commons.dbutils.BeanProcessor#mapColumnsToProperties(java.sql.ResultSetMetaData, java.beans.PropertyDescriptor[])
	 */
	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
		// TODO Auto-generated method stub
		
		int cols = rsmd.getColumnCount();
        int columnToProperty[] = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

        for (int col = 1; col <= cols; col++) {
            String columnName = rsmd.getColumnName(col);
            columnName = processColumnName(columnName);
            for (int i = 0; i < props.length; i++) {

                if (columnName.equalsIgnoreCase(props[i].getName())) {
                    columnToProperty[col] = i;
                    break;
                }
            }
        }

        return columnToProperty;
		
	}
	
	private String processColumnName(String oldName) {
		if (StringUtils.isBlank(oldName)) {
			return "";
		}
		String newName = null;
		String[] tmpNames = oldName.split("_");
		StringBuffer sb = new StringBuffer();
		sb.append(tmpNames[0]);
		for (int i=1; i<tmpNames.length; i++) {
			String tmpname = tmpNames[i];
			String tmp1 = tmpname.substring(0, 1);
			tmp1 = tmp1.toUpperCase();
			tmpname = tmp1 + tmpname.substring(1, tmpname.length());
			sb.append(tmpname);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		TwitterBeanProcessor p = new TwitterBeanProcessor();
		String t1 = "user_true_n";
		System.out.println(p.processColumnName(t1));
	}

}
