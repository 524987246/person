package org.great.util.lucene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;

public class test {
	public static String queryString = "1";
	public static String[] highlighter = { "name", "content" };

	public static void main(String[] args) {
		try {
			List<LuceneBean> list = getTestDb();
			// System.out.println("当前使用的分词器：IKAnalyzer(false)");
			// LuceneUtil luceneUtil2=new
			// LuceneUtil("E:\\Lucene\\ikanalyzerfalse", new IKAnalyzer(false));
			// luceneUtil2.index(list);// 初始化
			// test1(luceneUtil2);
			System.out.println("当前使用的分词器：WhitespaceAnalyzer()");
			LuceneUtil luceneUtil1 = new LuceneUtil("E:\\Lucene\\whitespaceanalyzer", new WhitespaceAnalyzer(),
					highlighter);
			luceneUtil1.init(list);// 初始化
			test1(luceneUtil1);
			// System.out.println("当前使用的分词器：IKAnalyzer(true)");
			// LuceneUtil luceneUtil3 = new
			// LuceneUtil("E:\\Lucene\\ikanalyzertrue", new IKAnalyzer(true),
			// highlighter);
			// luceneUtil3.init(list);// 初始化
			// test1(luceneUtil3);
			// System.out.println("当前使用的分词器：StandardAnalyzer()");
			// LuceneUtil luceneUtil4=new
			// LuceneUtil("E:\\Lucene\\standardanalyzer", new
			// StandardAnalyzer());
			// luceneUtil4.index(list);// 初始化
			// test1(luceneUtil4);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void test1(LuceneUtil luceneUtil) {
		System.out.println("%%%%%%%%%%%%%%=test1=%%%%%%%%%%%%%%%%%%%%%%%%");
		try {
			long start = System.currentTimeMillis();
			List<Map<String, Object>> list = luceneUtil.search("name", queryString, null, 1, 10);
			long end = System.currentTimeMillis();
			for (Map<String, Object> map : list) {
				MyPrintUtil.printMap(map);
				System.out.println("====");
			}
			 System.out.println("test1 time:" + (end - start));
			// if (list != null) {
			// for (LuceneBean luceneBean : list) {
			// System.out.println(luceneBean.toString());
			// }
			 System.out.println(list.size());
			// }
			// System.out.println("===========================");
			// list = luceneUtil.search("name", "12", 2, 10);
			// if (list != null) {
			// for (LuceneBean luceneBean : list) {
			// System.out.println(luceneBean.toString());
			// }
			// System.out.println(list.size());
			// } else {
			// System.out.println("list is null");
			// }

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 获取测试数据
	 * 
	 * @return
	 */
	public static List<LuceneBean> getTestDb() {
		List<LuceneBean> list = new ArrayList<LuceneBean>();
		DbBaseInfo baseInfo_old = new DbBaseInfo();
		baseInfo_old.setDriver(Dboperate.MYSQL_DRIVER);
		baseInfo_old.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=UTF-8");
		baseInfo_old.setUsername("root");
		baseInfo_old.setUserpwd("123456");
		Connection con = Dboperate.getConnection(baseInfo_old);
		String sql = "select * from pd_ugc";
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				LuceneBean bean = new LuceneBean();
				bean.setId(resultSet.getString("id"));
				bean.setName(resultSet.getString("name"));
				bean.setContent(resultSet.getString("citycode"));
				bean.setFlag(i);
				list.add(bean);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
