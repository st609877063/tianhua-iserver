package com.platform.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;

import com.platform.utils.DateUtil;


public class ControlDatabase {
    
    private static Connection conn =null;
    private static Statement usersStatement =null;
    private static Statement operationsStatement =null;
    private static Statement magazineClassStatement =null;
    
    private static ResultSet usersResultSet =null;
    private static ResultSet operationsResultSet =null;
    private static ResultSet magazineClassResultSet =null;
    private static Logger logger = Logger.getLogger(InitData.class.getName());
    public static synchronized Connection getConnection(String driver,String url,String user,String password){
    	loadDriver(driver);
		try {
			if(conn==null){
				conn = DriverManager.getConnection(url,user,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
    }

    private static void loadDriver(String driver) {
        try {
            Class.forName(driver).newInstance();
            logger.info("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            logger.error("\nUnable to load the JDBC driver " + driver);
            logger.error("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
        	 logger.error(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
        	 logger.error(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    public static void init(String driver,String url,String user,String password){

    	conn = getConnection(driver,url,user,password);
    	try {
    		
    		usersStatement = conn.createStatement();
			operationsStatement = conn.createStatement();
			magazineClassStatement = conn.createStatement();
			//初始化用户信息
			int usersCount = 0;
			usersResultSet = usersStatement.executeQuery("select count(user_id) from users");
			if(usersResultSet.next())
				usersCount = usersResultSet.getInt(1);
			if(usersCount==0){
				logger.info("User data initialization...");	
				usersStatement.execute("insert into users(user_id,name,password,valid) values(1,'admin','21232f297a57a5a743894ae4a801fc3',0)");
    		}
			//初始化操作列表
			int operationsCount = 0;
			operationsResultSet = operationsStatement.executeQuery("select count(operation_id) from operation");
			if(operationsResultSet.next())
				operationsCount =	operationsResultSet.getInt(1);
			if(operationsCount==0){
				logger.info("Operation data initialization...");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('1','杂志管理',1,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('2','评论管理',2,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('3','用户管理',3,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('4','日志管理',4,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('5','计费设置',5,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('6','备份管理',6,1,0)");
				operationsStatement.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('7','还原管理',7,1,0)");
			}
			//初始化杂志分类
			int magazineClassCount = 0;
			magazineClassResultSet = magazineClassStatement.executeQuery("select count(magazine_class_id) from magazine_class");
			if(magazineClassResultSet.next())
				magazineClassCount = magazineClassResultSet.getInt(1);
			if(magazineClassCount==0){
				logger.info("MagazineClass data initialization...");	
				magazineClassStatement.execute("insert into " +
						"magazine_class(magazine_class_id,magazine_class_name) " +
						" values('1','小康')");
				magazineClassStatement.execute("insert into " +
						"magazine_class(magazine_class_id,magazine_class_name)" +
						" values('2','财智')");
    		}
			//初始化杂志信息
//			int magazinesCount = 0;
//			magazinesResultSet = magazinesStatement.executeQuery("select count(magazine_id) from magazine");
//			if(magazinesResultSet.next())
//				magazinesCount = magazinesResultSet.getInt(1);
//			if(magazinesCount==0){
//				Date d = new Date();
//				String date = DateUtil.formatDate(d);
//				logger.info("Magazine data initialization...");	
//				magazinesStatement.execute("insert into " +
//						"magazine(magazine_id,magazine_class_id,magazine_name,magazine_picture,user_id,create_date,download_counter,phase,update_date,view_counter,cost) " +
//						" values('1','1','小康01','1.jpg','0','"+date+"',0,'201101','"+date+"',0,0)");
//				magazinesStatement.execute("insert into " +
//						"magazine(magazine_id,magazine_class_id,magazine_name,magazine_picture,user_id,create_date,download_counter,phase,update_date,view_counter,cost)" +
//						" values('2','2','财智01','2.jpg','0','"+date+"',0,'201102','"+date+"',0,0)");
//    		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
    }
    
    public static boolean backup(String fPath) {
    	try {
    	 StringBuffer sb = new StringBuffer();
    	  sb.append(" mysqldump ");
    	  sb.append("--opt ");
    	  sb.append("-h ");
    	  sb.append("localhost");
    	  sb.append(" ");
    	  sb.append("--user=");
    	  sb.append("root");
    	  sb.append(" ");
    	  sb.append("--password=");
    	  sb.append("root");
    	  sb.append(" ");
    	  sb.append("--lock-all-tables=true ");
    	  sb.append("--result-file=");
    	  sb.append(fPath);
    	  sb.append(" ");
    	  sb.append("--default-character-set=utf8 ");
    	  sb.append("ipdb");
    	  Runtime cmd = Runtime.getRuntime();
    	  System.out.println(sb.toString());
    	  cmd.exec(sb.toString());

    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return true;
    	}


    	public static boolean load(String fPath) {
    	try {
    		  // 新建数据库newdb
    		  //String stmt1 = "mysqladmin -u " + root + " -p" + pass + " create newdb";
    		  // -p后面加的是你的密码
    		  String address = "localhost";
    		  String root = "root";
    		  String pass = "root";
    		  String databaseName = "ipdb";
    		  String stmt2 = "mysql -h"+ address+" -u" + root + " -p" + pass +" "+databaseName+ "  < "
    		    + fPath;
    		  System.out.println("开始完全恢复: "+stmt2);
    		  String[] cmd = { "cmd", "/c", stmt2 };
    		  Runtime.getRuntime().exec(cmd);
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return true;
    	}
	
    	public static void closeConnection(Connection con){
		try{
			if(con!=null){
				con.close();
			}
			}catch(Exception e){
			e.printStackTrace();
		}
	}
}
