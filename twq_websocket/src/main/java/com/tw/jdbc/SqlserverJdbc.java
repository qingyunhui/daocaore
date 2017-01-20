package com.tw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlserverJdbc {
	private Connection Conn = null;
    private Statement Stmt = null;
    private ResultSet Rs = null;
    public SqlserverJdbc() {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Conn = java.sql.DriverManager.getConnection("jdbc:sqlserver://192.168.130.10:1433;DatabaseName=unionman", "sa","123abc");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    public void Execute(String sql) throws Exception{
    	Stmt = Conn.createStatement();
		Stmt.execute(sql);
    }
    public ResultSet ExecuteQuery(String sql) throws Exception{
    	Conn.setAutoCommit(false);
		Stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		Rs = Stmt.executeQuery(sql);
    	return Rs;
    }
    
    public void ExecuteUpdate(String sql) throws Exception{
        PreparedStatement PreStmt = Conn.prepareStatement(sql);
        PreStmt.executeUpdate();
        PreStmt.close();
    }
    
	public int getCountResult(String sql) {
		 Rs = null;
		int allrow = 0;
		try {
			Conn.setAutoCommit(false);
			// 创建一个JDBC声明
			Stmt = Conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			Rs = Stmt.executeQuery(sql);
			Rs.last();
			allrow = Rs.getRow();
			return allrow;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
    
    public void Close() {
        try {
            if (Rs != null) {
                Rs.close();
            }
            if (Stmt != null) {
                Stmt.close();
            }
            if (Conn != null) {
                Conn.close();
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
}
