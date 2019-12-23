package com.zm.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.PreparedStatement;


public class JDBCUtil {
  private static DataSource dataSource;
  
  static{ 
	  
	  try {
	  Properties pro = new Properties();
      InputStream stream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
	  pro.load(stream);
	   
	   //获取数据库连接池对象
	  dataSource = DruidDataSourceFactory.createDataSource(pro);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  //获得Connection对象
  public static Connection getConn() throws SQLException {
	  return dataSource.getConnection();
  }
  
  //关闭资源连接
  public static void close(Connection conn,ResultSet rs,PreparedStatement ps) throws SQLException {
	 
	  if(rs!=null){
		  rs.close();
	  }
	  
	  if(ps!=null){
		  ps.close();
	  }
	  
	  if(conn!=null){
		  conn.close();
	  }
  }
  
}
