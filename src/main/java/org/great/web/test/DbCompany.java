package org.great.web.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.great.util.Dboperate;
import org.great.web.bean.sys.DbName;

public class DbCompany {
	public static DbName olddbName = new DbName();
	public static DbName newdbName = new DbName();
	public static Connection old_con;
	public static Connection new_con;

	public static List<OldCarInfo> old_car_list;
	public static List<BuzCarinfo> new_car_list;
	public static List<OldCompanyInfo> old_company_list;
	public static List<BuzCompany> new_company_list;

	private static String query_all_oldcarinfo = "select a.CARID carId,a.CAR_MARK carMark,a.COMPANYID companyId from GPSDB_BASE.dbo.CAR_INFO a";
	private static String query_all_oldcompanyinfo = "select b.LIST list,b.SURROGATE parentid,b.list2,b.list3,b.COMPANY_NAME companyName,b.COMPANYID companyId,b.STATUS companyStatus  from GPSDB_BASE.dbo.COMPANY_INFO b where b.STATUS=1 ORDER BY b.COMPANY_NAME";
	private static String query_all_newcarinfo = "select a.car_num carNum,a.id id,a.del_flag delFlag from buz_carinfo a";
	private static String query_all_newcompanyinfo = "SELECT a.id,a.name,a.parent_id parentId,a.parent_ids parentIds,a.del_flag delFlag FROM buz_company a where a.del_flag=0 ORDER BY a.name";

	static {
		newdbName.setSdriver("com.mysql.jdbc.Driver");
		newdbName.setSurl("jdbc:mysql://localhost:3306/kaisioa?useUnicode=true&amp;characterEncoding=UTF-8");
		newdbName.setUsername("root");
		newdbName.setUserpwd("JF1602");

		olddbName.setSdriver("");
		olddbName.setSurl("");
		olddbName.setUsername("");
		olddbName.setUserpwd("");

		old_con = getConnection(olddbName);
		new_con = getConnection(newdbName);
		init();
	}

	public static Connection getConnection(DbName dbName) {
		Connection con = Dboperate.getConnection(dbName.getSdriver(), dbName.getSurl(), dbName.getUsername(),
				dbName.getUserpwd());
		return con;
	}

	public static List<OldCarInfo> getOldCarinfo(Connection con) {
		List<OldCarInfo> list = new ArrayList<OldCarInfo>();
		try {
			PreparedStatement stmt = con.prepareStatement(query_all_oldcarinfo);
			ResultSet set = stmt.executeQuery();
			while (true) {
				if (set.next()) {
					OldCarInfo test = new OldCarInfo();
					test.setCarId(set.getString("carId"));
					test.setCarMark(set.getString("carMark"));
					test.setCompanyId(set.getString("companyId"));
					list.add(test);
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getOldCarinfo.size=" + list.size());
		return list;
	}

	public static List<OldCompanyInfo> getOldCompanyinfo(Connection con) {
		List<OldCompanyInfo> list = new ArrayList<OldCompanyInfo>();
		try {
			PreparedStatement stmt = con.prepareStatement(query_all_oldcompanyinfo);
			ResultSet set = stmt.executeQuery();
			while (true) {
				if (set.next()) {
					OldCompanyInfo test = new OldCompanyInfo();
					String str = "";
					str = set.getString("parentid");
					test.setCompanyId(set.getString("companyId"));
					test.setList(set.getString("list"));
					if (set.getString("list").equals(set.getString("companyId"))) {
						test.setParentid("0");
					} else if (str.equals(set.getString("companyId"))) {
						test.setParentid("0");
					} else if (str.equals("0")) {
						test.setParentid(set.getString("list"));
					} else {
						test.setParentid(str);
					}
					test.setList2(set.getString("list2"));
					test.setList3(set.getString("list3"));
					test.setCompanyName(set.getString("companyName"));
					test.setCompanyStatus(set.getString("companyStatus"));
					list.add(test);
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getOldCompanyinfo.size=" + list.size());
		return list;
	}

	public static List<BuzCarinfo> getNewCarinfo(Connection con) {
		List<BuzCarinfo> list = new ArrayList<BuzCarinfo>();
		try {
			PreparedStatement stmt = con.prepareStatement(query_all_newcarinfo);
			ResultSet set = stmt.executeQuery();
			while (true) {
				if (set.next()) {
					BuzCarinfo test = new BuzCarinfo();
					test.setId(set.getString("id"));
					test.setCarNum(set.getString("carNum"));
					test.setDelFlag(set.getString("delFlag"));
					list.add(test);
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getNewCarinfo.size=" + list.size());
		return list;
	}

	public static List<BuzCompany> getNewCompanyinfo(Connection con) {
		List<BuzCompany> list = new ArrayList<BuzCompany>();
		try {
			PreparedStatement stmt = con.prepareStatement(query_all_newcompanyinfo);
			ResultSet set = stmt.executeQuery();
			while (true) {
				if (set.next()) {
					BuzCompany test = new BuzCompany();
					test.setId(set.getString("id"));
					test.setName(set.getString("name"));
					test.setParentId(set.getString("parentId"));
					test.setParentIds(set.getString("parentIds"));
					test.setDelFlag(set.getString("delFlag"));
					list.add(test);
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getNewCompanyinfo.size=" + list.size());
		return list;
	}

	public static void init() {
		old_car_list = getOldCarinfo(old_con);
		new_car_list = getNewCarinfo(new_con);
		old_company_list = getOldCompanyinfo(old_con);
		new_company_list = getNewCompanyinfo(new_con);
	}

	/**
	 * 获取服务器数据中,本地数据没有的公司
	 * 
	 * @return 公司集合(id,name,parentid)
	 */
	public static List<BuzCompany> getAddCompany() {
		List<BuzCompany> list = new ArrayList<BuzCompany>();
		Map<String, BuzCompany> new_company_set = new HashMap<String, BuzCompany>();
		Map<String, OldCompanyInfo> old_company_set = new HashMap<String, OldCompanyInfo>();
		// 本系统公司信息 set 集合
		for (BuzCompany temp : new_company_list) {
			new_company_set.put(temp.getId(), temp);
		}
		// 服务器公司信息 set 集合
		for (OldCompanyInfo temp : old_company_list) {
			old_company_set.put(temp.getCompanyId(), temp);
		}
		boolean bo = false;
		// 比较两个数据库中公司,存放服务器 新增的公司
		// 服务器中,存在删除父类,却没有删除子公司的情况
		Map<String, BuzCompany> map = new HashMap<String, BuzCompany>();
		for (OldCompanyInfo temp : old_company_list) {
			bo = new_company_set.containsKey(temp.getCompanyId());
			if (!bo) {
				BuzCompany buzCompany = new BuzCompany();
				buzCompany.setId(temp.getCompanyId());
				buzCompany.setName(temp.getCompanyName());
				String parentid = temp.getParentid();
				buzCompany.setParentId(parentid);
				bo = true;
				// 是否已经是顶级父类了
				while (!parentid.equals("2")) {
					//System.out.println("parentid==" + parentid);
					bo = old_company_set.containsKey(parentid);
					// 包含key true 包含 false 不包含
					if (bo) {
						parentid = old_company_set.get(parentid).getParentid();
					} else {
						break;
					}
				}
				if (bo) {

					// 父类存在 添加(子类父类均添加)
					parentid = buzCompany.getParentId();
					while (!parentid.equals("2")) {
						OldCompanyInfo companyInfo = old_company_set.get(parentid);
						if (!map.containsKey(parentid)) {
							BuzCompany buzCompany2 = new BuzCompany();
							buzCompany2.setId(companyInfo.getCompanyId());
							buzCompany2.setName(companyInfo.getCompanyName());
							buzCompany2.setParentId(companyInfo.getParentid());
							map.put(parentid, buzCompany2);
						}
						parentid = companyInfo.getParentid();
						// System.out.println(parentid);
					}
					map.put(buzCompany.getId(), buzCompany);
				}

			}
		}

		// 获取所有的value集合
		Iterator<String> iterator = map.keySet().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			list.add(map.get(key));
		}
		return list;
	}

	public static void main(String[] args) {
		List<BuzCompany> list = getAddCompany();
		for (BuzCompany buzCompany : list) {
			System.out.println(
					buzCompany.getId() + "  name:" + buzCompany.getName() + "  parentid:" + buzCompany.getParentId());
		}
	}
}
