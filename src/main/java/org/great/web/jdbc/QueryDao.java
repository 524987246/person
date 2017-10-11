package org.great.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.great.web.bean.buz.DbName;

public class QueryDao extends BaseDao {
	private final static String TABLEINFO_SQL = "SELECT table_name tableName, "
			+ " ENGINE, table_comment tableComment, "
			+ "create_time createTime" + " FROM information_schema.tables ";
	private final static String COLUMSINFO_SQL = "SELECT column_name columnName,"
			+ " data_type dataType,"
			+ " column_comment columnComment,"
			+ " column_key columnKey,"
			+ " column_comment comments,"
			+ " extra FROM information_schema.columns";

	public List<String> querydbnames(String stype, Connection con) {
		String sql = "";
		if (stype.equalsIgnoreCase("mysql")) {
			sql = "SELECT SCHEMA_NAME dbname FROM information_schema.SCHEMATA";
		}
		ResultSet rs = select(con, sql);
		List<String> list = new ArrayList<String>();
		while (true) {
			try {
				if (rs.next()) {
					list.add(rs.getString("dbname"));
				} else {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closestmt(con);
		return list;
	}

	public List<Map<String, String>> querytbnames(DbName dbName, Connection con) {
		String sql = "";
		if (dbName.getStype().equalsIgnoreCase("mysql")) {
			sql = TABLEINFO_SQL + " WHERE TABLE_SCHEMA = '" + dbName.getSname()
					+ "'";
			int begin = dbName.getPage_new() * dbName.getPage_num();
			int end = dbName.getPage_num() + 1;
			if (end != 0) {
				sql += "Limit " + begin + "," + end;
			}
		}
		ResultSet rs = select(con, sql);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		while (true) {
			try {
				if (rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("tableName", rs.getString("tableName"));
					map.put("ENGINE", rs.getString("ENGINE"));
					map.put("tableComment", rs.getString("tableComment"));
					map.put("createTime", rs.getString("createTime"));
					list.add(map);
				} else {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closestmt(con);
		return list;
	}

	public Map<String, String> queryTable(DbName dbName, String tableName,
			Connection con) {
		String sql = "";
		if (dbName.getStype().equalsIgnoreCase("mysql")) {
			sql = TABLEINFO_SQL
					+ " WHERE table_schema = (SELECT DATABASE()) AND table_name = '"
					+ tableName + "'";
		}
		ResultSet rs = select(con, sql);
		Map<String, String> map = new HashMap<String, String>();
		while (true) {
			try {
				if (rs.next()) {
					map.put("tableName", rs.getString("tableName"));
					map.put("tableComment", rs.getString("tableComment"));
					map.put("engine", rs.getString("engine"));
				} else {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public List<ColumnEntity> queryColumns(DbName dbName, String tableName,
			Connection con) {
		String sql = "";
		if (dbName.getStype().equalsIgnoreCase("mysql")) {
			sql = COLUMSINFO_SQL
					+ " WHERE table_name ='"
					+ tableName
					+ "' and table_schema = (select database()) order by ordinal_position";
		}
		ResultSet rs = select(con, sql);
		List<ColumnEntity> list = new ArrayList<ColumnEntity>();
		while (true) {
			try {
				if (rs.next()) {
					ColumnEntity columnEntity = new ColumnEntity();
					columnEntity.setColumnName(rs.getString("columnName"));
					columnEntity.setDataType(rs.getString("dataType"));
					columnEntity.setComments(rs.getString("comments"));
					list.add(columnEntity);
				} else {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
