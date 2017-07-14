package org.great.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dboperate {
	public static Connection getConnection(String driver, String url,
			String username, String userpwd) {
		Connection con = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		try {
			con = DriverManager.getConnection(url, username, userpwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_person?useUnicode=true&amp;characterEncoding=UTF-8";
		String username = "root";
		String userpwd = "JF1602";
		Connection con = getConnection(driver, url, username, userpwd);
//		System.out.println(con);
		String sql = "SELECT * FROM tb_costinfo WHERE income_expend=1 AND createtime>='2017-03-01'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet set = stmt.executeQuery();
		while (true) {
			if (set.next()) {
				System.out.print(set.getString("createtime")+"==");
				System.out.print(set.getString("endtime")+"==");
				System.out.print(set.getString("payname")+"==");
				System.out.print(set.getString("paymoney")+"==");
				System.out.print(set.getString("income_expend"));
				System.out.println();
			} else {
				break;
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// driver="oracle.jdbc.driver.OracleDriver";
		// url="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
		// username="JF1602";
		// userpwd="JF1602";
		// Connection con1=getConnection(driver,url,username,userpwd);
		// System.out.println(con1);
	}
}
