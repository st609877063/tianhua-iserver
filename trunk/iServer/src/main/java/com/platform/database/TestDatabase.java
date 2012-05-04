package com.platform.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestDatabase {
    
    private static Connection conn =null;
    private static Statement s =null;
    private static Statement st =null;
    private static Statement statement =null;
    private static ResultSet rs =null;
    private static ResultSet resultSet =null;
    private static ResultSet result =null;
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
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    public static void init(String driver,String url,String user,String password){
    	conn = getConnection(driver,url,user,password);
    	try {
    		//初始化用户信息
			s = conn.createStatement();
			st = conn.createStatement();
			statement = conn.createStatement();
			
			rs = s.executeQuery("select count(name) from users");
			int flag = 0;
			if(rs.next())
				flag = rs.getInt(1);
			if(flag==0){
				System.out.println("初始化数据..");	
				s.execute("insert into users(name,password) values('admin','哈哈')");
    		}
			//初始化操作列表
			int count = 0;
			resultSet = st.executeQuery("select count(name) from operation");
			if(resultSet.next())
				count =	resultSet.getInt(1);
			if(count==0){
				System.out.println("初始化操作列表..");
				st.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('1','用户管理',1,1,0)");
				st.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('2','权限管理',2,1,0)");
				st.execute("insert into operation(operation_id,name,seq,leaf,parent_id) values('3','日志管理',3,1,0)");
			}
			
			//查询操作列表
//			result = statement.executeQuery("select * from operation");
//			StringBuffer sb = new StringBuffer();
//			while(result.next()){
//				sb.append(result.getString(1));
//				sb.append("->");
//				sb.append(result.getString("name"));
//				sb.append("->");
//				sb.append(result.getString("seq"));
//				sb.append("\n");
//			}
//			System.out.println(sb);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//conn.commit();
				conn.close();
			    //DriverManager.getConnection("jdbc:derby:;shutdown=true");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
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
	public static void main(String args[]){
		init("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/ipdb?useUnicode=true&amp;characterEncoding=utf8","root","root");
		
	}
}
