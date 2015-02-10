package com.personal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

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
	
	public static Connection getConnection(String url, String username, String password) {
		try {
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			Properties info = new Properties();
			info.setProperty("user", username);
			info.setProperty("password", password);
			return driver.connect(url, info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
            iterateResultSet(rs);
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

	private static void iterateResultSet(ResultSet rs) throws SQLException {
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
	}
	
	public static void main(String[] args) {

		DataSource dataSource = null;
		
		String host = "localhost";
		String databaseName = "EMPLOYEE";
		String username = "root";
		String password = "root";
		String dmlQuery1 = "select * from Employment";
		String dmlQuery2 = "select * from Company";
		
		dataSource = getMySQLDataSource(host, databaseName);
		simpleQuery(dataSource, username, password, "show tables");
		
		dataSource = getMySQLPooledDataSource(host, databaseName);
		simpleQuery(dataSource, username, password, dmlQuery1);
		
		dataSource = getMySQLXADataSource(host, databaseName);
		simpleQuery(dataSource, username, password, dmlQuery2);

		
		System.out.println("******** Stored Procedure Call using " + " direct connection by registering driver" + " ********");
		
		Connection connection = null;
		CallableStatement stmt = null;
        ResultSet rs = null;
        try {
        	connection = getConnection("jdbc:mysql://localhost/EMPLOYEE", username, password);
            stmt = connection.prepareCall("Call GetAllNames()");
            rs = stmt.executeQuery();
            iterateResultSet(rs);
            
            DatabaseMetaData md = connection.getMetaData();
            System.out.println(String.format("\n%s %s", md.getDatabaseProductName(), md.getDatabaseProductVersion()));
			System.out.println("ResultSet.HOLD_CURSORS_OVER_COMMIT = "
					+ ResultSet.HOLD_CURSORS_OVER_COMMIT);

			System.out.println("ResultSet.CLOSE_CURSORS_AT_COMMIT = "
					+ ResultSet.CLOSE_CURSORS_AT_COMMIT);

			System.out.println("Default cursor holdability: "
					+ md.getResultSetHoldability());

			System.out
					.println("Supports HOLD_CURSORS_OVER_COMMIT? "
							+ md.supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT));

			System.out
					.println("Supports CLOSE_CURSORS_AT_COMMIT? "
							+ md.supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT));
			
			System.out.println();
			
			System.out.println("ResultSet.TYPE_FORWARD_ONLY = "
					+ ResultSet.TYPE_FORWARD_ONLY);
			System.out.println("ResultSet.TYPE_SCROLL_SENSITIVE = "
					+ ResultSet.TYPE_SCROLL_INSENSITIVE);
			System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE = "
					+ ResultSet.TYPE_SCROLL_INSENSITIVE);
			
			
			System.out.println("Supports ResultSet.TYPE_FORWARD_ONLY? "
					+ md.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
			System.out.println("Supports ResultSet.TYPE_SCROLL_SENSITIVE? "
					+ md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println("Supports ResultSet.TYPE_SCROLL_INSENSITIVE? "
					+ md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			
			System.out.println();
			
			System.out.println("ResultSet.CONCUR_READ_ONLY = "
					+ ResultSet.CONCUR_READ_ONLY);
			System.out.println("ResultSet.CONCUR_UPDATABLE = "
					+ ResultSet.CONCUR_UPDATABLE);
			
			System.out.println("Supports ResultSet.CONCUR_READ_ONLY? "
					+ md.supportsResultSetType(ResultSet.CONCUR_READ_ONLY));
			System.out.println("Supports ResultSet.CONCUR_UPDATABLE? "
					+ md.supportsResultSetType(ResultSet.CONCUR_UPDATABLE));
			
			
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) rs.close();
                    if(stmt != null) stmt.close();
                    if(connection != null) {System.out.println();connection.close();}
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
	}

}
