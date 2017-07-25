package org.great.web.service;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.great.util.Dboperate;
import org.great.web.bean.DbName;
import org.great.web.jdbc.BaseDao;
import org.great.web.jdbc.ColumnEntity;
import org.great.web.jdbc.QueryDao;
import org.great.web.mapper.SystemManageMapper;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class SystemManageService {
	@Resource
	private SystemManageMapper systemManageMapper;

	// public List<WebError> findWebErrorByWebError(WebError webError,
	// int page_new, int page_num) {
	// List<WebError> list = webErrorMapper.findWebErrorByWebError(webError,
	// page_new, page_num);
	// return list;
	// }

	public List<DbName> getDbName(DbName dbName) {
		List<DbName> list = systemManageMapper.getDbName(dbName);
		return list;
	}

	// public byte[] generatorCode(DbName dbName) {
	// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// ZipOutputStream zip = new ZipOutputStream(outputStream);
	// String[] tableNames = dbName.getTbnames();
	// Connection con = getConnection(dbName);
	// for (String tableName : tableNames) {
	// // 查询表信息
	// Map<String, String> table = queryTable(dbName,tableName, con);
	// // 查询列信息
	// List<Map<String, String>> columns = queryColumns(dbName,tableName, con);
	// //关闭连接
	// new BaseDao().closestmt(con);
	// // 生成代码
	// GenUtils.generatorCode(table, columns, zip);
	// }
	// IOUtils.closeQuietly(zip);
	// return outputStream.toByteArray();
	// }

	/**
	 * 查询表信息
	 * 
	 * @param stype
	 * @param sname
	 * @param con
	 * @return
	 */
	public Map<String, String> queryTable(DbName dbName, String tableName,
			Connection con) {
		QueryDao querydao = new QueryDao();
		Map<String, String> list = querydao.queryTable(dbName, tableName, con);
		return list;
	}

	/**
	 * 查询列信息
	 * 
	 * @param stype
	 * @param sname
	 * @param con
	 * @return
	 */
	public List<ColumnEntity> queryColumns(DbName dbName, String tableName,
			Connection con) {
		QueryDao querydao = new QueryDao();
		List<ColumnEntity> list = querydao.queryColumns(dbName, tableName, con);
		return list;
	}

	/**
	 * 查询所有表名
	 * 
	 * @param stype
	 * @param sname
	 * @param con
	 * @return
	 */
	public List<Map<String, String>> querytbnames(DbName dbName, Connection con) {
		QueryDao querydao = new QueryDao();
		List<Map<String, String>> list = querydao.querytbnames(dbName, con);
		return list;
	}

	/**
	 * 查询所有数据库名称
	 * 
	 * @param stype
	 * @param sname
	 * @param con
	 * @return
	 */
	public List<String> querydbnames(String stype, Connection con) {
		QueryDao querydao = new QueryDao();
		List<String> list = querydao.querydbnames(stype, con);
		return list;
	}

	/**
	 * 测试数据库连接
	 * 
	 * @param dbName
	 * @return
	 */
	public Connection getConnection(DbName dbName) {
		DbName dbName2 = getDbName(dbName).get(0);
		String surl = dbName2.getSurl();
		surl = surl.replaceAll("ip:port", dbName.getSurl());
		if (dbName.getSname() != null && !dbName.getSname().trim().equals("")) {
			surl = surl.replaceAll("dbname", dbName.getSname());
		} else {
			surl = surl.replaceAll("/dbname", "");
		}
		Connection con = Dboperate.getConnection(dbName.getSdriver(), surl,
				dbName.getUsername(), dbName.getUserpwd());
		return con;
	}

	public List<ColumnEntity> generatorCode(DbName dbName, Connection con) {
		QueryDao querydao = new QueryDao();
		List<ColumnEntity> resultlist = querydao.queryColumns(dbName,
				dbName.getTbname(), con);
		return resultlist;
	}

	// /**
	// * 更改数据状态
	// *
	// * @param sid
	// * 主键
	// * @param isemploy
	// * 状态
	// * @return true 成功 false 失败
	// */
	// public boolean delWebErrorBySid(String sid, Integer isemploy) {
	// int i = 0;
	// if (sid.lastIndexOf(",") != -1) {
	// String[] arraysid = sid.split(",");
	// for (int j = 0; j < arraysid.length; j++) {
	// i = webErrorMapper.delWebErrorBySid(
	// Integer.valueOf(arraysid[j]), isemploy);
	// if (i == 0) {
	// break;
	// }
	// }
	// } else {
	// i = webErrorMapper.delWebErrorBySid(Integer.valueOf(sid), isemploy);
	// }
	//
	// boolean bo = false;
	// if (i > 0) {
	// bo = true;
	// }
	// return bo;
	// }
	//
	// /**
	// * 添加
	// *
	// * @param webError
	// * @return true 成功 false 失败
	// */
	// public boolean insertWebError(WebError webError) {
	// int i = webErrorMapper.insertWebError(webError);
	// boolean bo = false;
	// if (i > 0) {
	// bo = true;
	// }
	// return bo;
	// }
	//
	// /**
	// * 更新信息
	// *
	// * @param webError
	// * @return true 成功 false 失败
	// */
	// public boolean updateWebErrorBySid(WebError webError) {
	// int i = webErrorMapper.updateWebErrorBySid(webError);
	// boolean bo = false;
	// if (i > 0) {
	// bo = true;
	// }
	// return bo;
	// }
}
