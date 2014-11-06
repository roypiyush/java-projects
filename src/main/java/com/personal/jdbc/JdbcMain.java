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

	public static DataSource getMySQLDataSource() {
        
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setServerName("localhost");
            mysqlDS.setDatabaseName("EMPLOYEE");
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
            mysqlDS.setDatabaseName("EMPLOYEE");
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
            mysqlDS.setDatabaseName("EMPLOYEE");
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
            rs = stmt.executeQuery("select * from Employment;");
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
		
		dataSource = getMySQLDataSource();
		simpleQuery(dataSource);
		
		dataSource = getMySQLPooledDataSource();
		simpleQuery(dataSource);
		
		dataSource = getMySQLXADataSource();
		simpleQuery(dataSource);

	}

}
