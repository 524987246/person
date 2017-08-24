package org.great.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dboperate {
	public static Connection getConnection(String driver, String url, String username, String userpwd) {
		Connection con = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		try {
			con = DriverManager.getConnection(url, username, userpwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kaisioa?useUnicode=true&amp;characterEncoding=UTF-8";
		String username = "root";
		String userpwd = "JF1602";


		Connection con = getConnection(driver, url, username, userpwd);
		// Connection con2 = getConnection(driver2, url2, username2, userpwd2);
		// System.out.println(con);
		String sql = "select id,del_flag,parent_id,surrogate,name from buz_company";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet set = stmt.executeQuery();
		List<Testbean> list = new ArrayList<Testbean>();

		while (true) {
			if (set.next()) {
				Testbean testbean = new Testbean();
				String sbf = "";
				String str = null;
				str = set.getString("id");
				testbean.id = str;
				str = set.getString("name");
				testbean.name = str;
//				str = set.getString("surrogate");
//				testbean.surrogate = str;
//				str = set.getString("del_flag");
//				if (str.equals("0")) {
//					testbean.del_flag = "1";
//				} else {
//					testbean.del_flag = "0";
//				}
//				str = set.getString("parent_id");
//				testbean.parent_id=str;
//				System.out.print(testbean.id);
//				if(str==null||str.trim().equals("")){
//					System.exit(-1);
//				}
				while (!str.equals(testbean.id)) {
					String flag = str;
					sbf = str + "," + sbf;
					str = getparent(sql, str, con);
					if (str == null) {
						testbean.del_flag = "1";
						System.out.println("name="+testbean.name);
						list.add(testbean);
						break;
					}else if(str.equals(flag)){
						break;
					}
				}
//				if(!testbean.surrogate.equals("0")){
//					sbf = sbf+testbean.surrogate+",";
//					testbean.parent_id=testbean.surrogate;
//				}
				// sbf = str + "," + sbf;
//				sbf = sbf.trim();
//				if(sbf.equals("")){
//					sbf = str + "," + sbf;
//				}
//				testbean.parent_ids = sbf;
//				System.out.println("===" + sbf+"<parent_id="+testbean.parent_id+">");
				
			} else {
				break;
			}
		}
		System.out.println("要更新的集合长度" + list.size());
//		sql = "UPDATE buz_company SET del_flag='A1' WHERE id=A3";
//		try {
//			stmt.close();
//			if (set != null) {
//				set.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		PreparedStatement stmt2 = con.prepareStatement(sql);
//		int i = 1;
//		for (Testbean temp : list) {
//			String flag = sql.replaceAll("A1", temp.del_flag);
//			//flag = flag.replaceAll("A2", temp.parent_ids);
//			flag = flag.replaceAll("A3", temp.id);
//			System.out.println(i + "==" + flag);
//			i++;
//			int j = stmt2.executeUpdate(flag);
//			if (j > 0) {
//				System.out.println(true);
//			} else {
//				System.out.println(false);
//			}
//		}

		// driver="oracle.jdbc.driver.OracleDriver";
		// url="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
		// username="JF1602";
		// userpwd="JF1602";
		// Connection con1=getConnection(driver,url,username,userpwd);
		// System.out.println(con1);
	}

	private static String getparent(String sql, String id, Connection con) throws SQLException {
		sql = sql + " where id='" + id+"'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet set = stmt.executeQuery();
		while (true) {
			if (set.next()) {
				sql = set.getString("parent_id");
				return sql;
			} else {
				break;
			}
		}
		return null;
	}
}

class Testbean {
	public String id;
	public String del_flag;
	public String surrogate;
	public String parent_id;
	public String parent_ids;
	public String name;

}
