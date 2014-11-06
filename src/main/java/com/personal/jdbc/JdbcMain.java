package com.personal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class JdbcMain {

	public static DataSource getMySQLDataSource() {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setServerName("localhost");
            mysqlDS.setDatabaseName("mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	public static DataSource getMySQLPooledDataSource() {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlConnectionPoolDataSource();
            mysqlDS.setServerName("localhost");
            mysqlDS.setDatabaseName("mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	public static DataSource getMySQLXADataSource() {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlXADataSource();
            mysqlDS.setServerName("localhost");
            mysqlDS.setDatabaseName("mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	static void simpleQuery(DataSource dataSource) {
		
		System.out.println("******** Querying using " + dataSource.getClass().getCanonicalName() + " ********");
		
		Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection("root", "root");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select host, user from user;");
            while(rs.next()){
                System.out.println("Host="+rs.getString("host")+", User="+rs.getString("user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
                try {
                    if(rs != null) rs.close();
                    if(stmt != null) stmt.close();
                    if(con != null) {System.out.println();con.close();}
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
	}
	
	public static void main(String[] args) {

		DataSource dataSource = null;
		
		dataSource = getMySQLDataSource();
		simpleQuery(dataSource);
		
		dataSource = getMySQLPooledDataSource();
		simpleQuery(dataSource);
		
		dataSource = getMySQLXADataSource();
		simpleQuery(dataSource);

	}

}
