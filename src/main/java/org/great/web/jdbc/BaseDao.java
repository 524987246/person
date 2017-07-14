package org.great.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	ResultSet set;
	protected PreparedStatement stmt;

	public boolean createtable(Connection con, String sql) {
		boolean b = false;
		try {
			stmt = con.prepareStatement(sql);
			b = stmt.execute();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		closestmt(con);
		return b;
	}

	ResultSet select(Connection con, String sql, String... args) {
		set = null;
		try {
			stmt = con.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					stmt.setString(i + 1, args[i]);
				}
			}
			set = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}

	int update(Connection con, String sql, String... args) {
		int row = 0;
		synchronized (BaseDao.class) {
			try {
				stmt = con.prepareStatement(sql);
				if (args != null) {
					for (int i = 0; i < args.length; i++) {
						stmt.setString(i + 1, args[i]);
					}
				}
				row = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}

	public void closestmt(Connection con) {
		try {
			if (set != null) {
				set.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
