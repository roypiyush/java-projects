package com.personal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class JdbcMain {

	public static DataSource getMySQLDataSource(String host, String databaseName) {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setServerName(host);
            mysqlDS.setDatabaseName(databaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	public static DataSource getMySQLPooledDataSource(String host, String databaseName) {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlConnectionPoolDataSource();
            mysqlDS.setServerName(host);
            mysqlDS.setDatabaseName(databaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	public static DataSource getMySQLXADataSource(String host, String databaseName) {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlXADataSource();
            mysqlDS.setServerName(host);
            mysqlDS.setDatabaseName(databaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
	
	static void simpleQuery(DataSource dataSource, String username, String password, String dmlQuery) {
		
		System.out.println("******** Querying using " + dataSource.getClass().getCanonicalName() + " ********");
		
		Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection(username, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(dmlQuery);
            while(rs.next()){
            	ResultSetMetaData metaData = rs.getMetaData();
            	int columnCount = metaData.getColumnCount();
            	
            	String output = "";
            	for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					int columnType = metaData.getColumnType(i);
					
					switch(columnType) {
					case Types.INTEGER:
						output += columnName + ": " + rs.getInt(i) + "  ";
						break;
					case Types.VARCHAR:
						output += columnName + ": " + rs.getString(i) + "  ";
						break;
					};
				}
            	System.out.println(output);
            	
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
		
		String host = "localhost";
		String databaseName = "EMPLOYEE";
		String username = "root";
		String password = "root";
		String dmlQuery1 = "select * from Employment";
		String dmlQuery2 = "select * from Name";
		
		dataSource = getMySQLDataSource(host, databaseName);
		simpleQuery(dataSource, username, password, "show tables");
		
		dataSource = getMySQLPooledDataSource(host, databaseName);
		simpleQuery(dataSource, username, password, dmlQuery1);
		
		dataSource = getMySQLXADataSource(host, databaseName);
		simpleQuery(dataSource, username, password, dmlQuery2);

	}

}
