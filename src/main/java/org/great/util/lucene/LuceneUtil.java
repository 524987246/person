package org.great.util.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * 
 * @描述 对数据库数据进行搜索
 */
public class LuceneUtil {

	/**
	 * 索引文件保存路径
	 */
	private String INDEX_DIR = "E:\\Lucene\\index";

	/**
	 * 高亮头标签
	 */
	private String prefixHTML = "<font color='red'>";

	/**
	 * 高亮尾标签
	 */
	private String suffixHTML = "</font>";
	/**
	 * 定义高亮属性
	 */
	private String[] highlighter;
	/**
	 * 定义高亮属性
	 */
	private Set<String> highlighterField = new HashSet<String>();;
	/**
	 * 分词器类型
	 */
	private Analyzer analyzer;
	/**
	 * 索引
	 */
	private IndexWriter indexWriter = null;

	/**
	 * 初始化 lucene
	 * 
	 * @param iNDEX_DIR
	 *            索引文件保存路径
	 * @param prefixHTML
	 *            高亮头标签
	 * @param suffixHTML
	 *            高亮尾标签
	 * @param analyzer
	 *            定义分词器类型
	 * @param highlighter
	 *            定义高亮属性
	 */
	public LuceneUtil(String iNDEX_DIR, String prefixHTML, String suffixHTML, Analyzer analyzer, String[] highlighter) {
		super();
		this.INDEX_DIR = iNDEX_DIR;
		this.prefixHTML = prefixHTML;
		this.suffixHTML = suffixHTML;
		this.analyzer = analyzer;
		this.highlighter = highlighter;
	}

	/**
	 * 初始化 lucene
	 * 
	 * @param iNDEX_DIR
	 *            索引文件保存路径
	 * @param analyzer
	 *            定义分词器类型
	 * @param highlighter
	 *            定义高亮属性
	 */
	public LuceneUtil(String iNDEX_DIR, Analyzer analyzer, String[] highlighter) {
		super();
		this.INDEX_DIR = iNDEX_DIR;
		this.analyzer = analyzer;
		this.highlighter = highlighter;
	}

	/**
	 * 建立索引
	 * 
	 * @param list
	 *            需要搜索的全部数据
	 * @throws Exception
	 */
	public void init(List<? extends Object> list) throws Exception {
		// 清空之前的文件,重新建立索引
		deleteAllIndex(true);
		File indexFile = new File(INDEX_DIR);
		if (!indexFile.exists()) {
			indexFile.mkdirs();
		}
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		indexWriter = new IndexWriter(directory, config);
		for (Object obj : list) {
			Document document = new Document();
			// 反射获取对象中全部属性及属性值
			List<java.lang.reflect.Field> field_list = new ArrayList<java.lang.reflect.Field>();
			getAllfield(field_list, obj.getClass());
			java.lang.reflect.Field[] fields = new java.lang.reflect.Field[field_list.size()];
			for (int i = 0; i < field_list.size(); i++) {
				fields[i] = field_list.get(i);
			}
			java.lang.reflect.Field.setAccessible(fields, true);
			// 所有值存入document
			for (java.lang.reflect.Field temp : fields) {
				String fieldName = temp.getName();
				Object value = temp.get(obj);

				if (value == null) {
					value = "";
				}
				document.add(new StringField(fieldName, value.toString(), Field.Store.YES));
				// String type = temp.getType().toString();
				// if (type.endsWith("String")) {
				// document.add(new StringField(fieldName, obj.toString(),
				// Field.Store.YES));
				// } else if (type.endsWith("int") || type.endsWith("Integer"))
				// {
				// document.add(new StringField(fieldName, obj.toString(),
				// Field.Store.YES));
				// } else if (type.endsWith("Long") || type.endsWith("long")) {
				// document.add(new StringField(fieldName, obj.toString(),
				// Field.Store.YES));
				// } else {
				// document.add(new StringField(fieldName, obj.toString(),
				// Field.Store.YES));
				// }
			}
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}

	/**
	 * 关键字查询
	 * 
	 * @param queryField
	 *            默认的查询属性名
	 * @param value
	 *            关键字
	 * @param sort
	 *            排序
	 * @param pageIndex
	 *            页数 从1开始
	 * @param pageSize
	 *            每页的个数
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> search(String queryField, String value, Sort sort, int pageIndex, int pageSize)
			throws Exception {
		QueryParser parser = new QueryParser(queryField, analyzer);
		parser.setAllowLeadingWildcard(true);
		String querie = "*" + value + "*";
		highlighterField.clear();
		if (this.highlighter != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			for (int i = 0; i < this.highlighter.length; i++) {
				highlighterField.add(this.highlighter[i]);
				if (i == 0) {
					buffer.append(this.highlighter[i] + ":" + querie);
				} else {
					buffer.append(" OR " + this.highlighter[i] + ":" + querie);
				}
			}
			buffer.append(")");
			querie = buffer.toString();
		} else {
			highlighterField.add(queryField);
		}
		if (sort == null) {
			SortField sortField = new SortField(queryField, SortField.Type.SCORE, false);
			sort = new Sort(sortField);
		}
		Query query = parser.parse(querie);
		return result(value, query, sort, pageIndex, pageSize);
	}

	/**
	 * 关键字查询
	 * 
	 * @param value
	 *            关键字
	 * @param query
	 *            查询对象
	 * @param sort
	 *            排序
	 * @param pageIndex
	 *            页数 从1开始
	 * @param pageSize
	 *            每页个数
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> result(String value, Query query, Sort sort, int pageIndex, int pageSize)
			throws Exception {
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIR));
		DirectoryReader ireader = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_DIR)));
		IndexSearcher isearcher = new IndexSearcher(ireader);
		int count = isearcher.count(query);// 获取查询最大数量
		// 总数30条 每页20条 2页
		if (pageIndex != 1) {
			int num = pageSize * (pageIndex - 1);
			System.out.println("count:" + count + " num:" + num);
			if (count < num) {
				return null;
			}
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		ScoreDoc lastScoreDoc = getLastScoreDoc(pageIndex, pageSize, query, sort, isearcher);
		TopDocs topDocs = null;
		if (lastScoreDoc != null) {
			topDocs = isearcher.searchAfter(lastScoreDoc, query, pageSize, sort);
		} else {
			topDocs = isearcher.search(query, pageSize, sort);
		}
		for (int i = 0; i < topDocs.scoreDocs.length; i++) {
			Document doc = isearcher.doc(topDocs.scoreDocs[i].doc);
			Map<String, Object> bean = printField(doc, value);
			result.add(bean);
		}
		ireader.close();
		directory.close();
		return result;
	}

	/**
	 * 根据页码和分页大小获取上一次的最后一个scoredocs
	 * 
	 * @param pageIndex
	 *            页数 从1开始
	 * @param pageSize
	 *            每页个数
	 * @param query
	 *            查询对象
	 * @param sort
	 *            排序
	 * @param searcher
	 * @return
	 * @throws IOException
	 */
	private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, Sort sort, IndexSearcher searcher)
			throws IOException {
		if (pageIndex == 1) {
			return null;// 如果是第一页就返回空
		}
		int num = pageSize * (pageIndex - 1);// 获取上一页的最后数量
		TopDocs tds = searcher.search(query, num, sort);
		return tds.scoreDocs[num - 1];
	}

	/**
	 * 删除索引
	 * 
	 * @param isdeletefile
	 */
	public void deleteAllIndex(boolean isdeletefile) {
		File index_file = new File(INDEX_DIR);
		if ((index_file.exists()) && (index_file.isDirectory())) {
			deleteAllFile();
		}
	}

	/**
	 * 删除文件
	 */
	private void deleteAllFile() {
		File index_file = new File(INDEX_DIR);
		File[] files = index_file.listFiles();
		for (int i = 0; i < files.length; i++)
			files[i].delete();
	}

	public void getAllfield(List<java.lang.reflect.Field> list, @SuppressWarnings("rawtypes") Class tempClass) {
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			java.lang.reflect.Field[] fields = tempClass.getDeclaredFields();
			for (java.lang.reflect.Field field : fields) {
				list.add(field);
			}
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
	}

	public Map<String, Object> printField(Document doc, String value) throws Exception {
		List<IndexableField> list = doc.getFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (IndexableField temp : list) {
			String str = doc.get(temp.name());
			if (highlighterField.contains(temp.name()) && str != null) {
				str = str.replaceAll(value, prefixHTML + value + suffixHTML);
			}
			map.put(temp.name(), str);
		}
		return map;
	}

	/**
	 * 更新一条索引 (更新一行,多条则更新最后一条,没有则新增)
	 * 
	 * @param term
	 * @param document
	 * @return
	 */
	public boolean updateDocument(Term term, Document document) {
		try {
			indexWriter.updateDocument(term, document);
			indexWriter.commit();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除一条/多条索引
	 * 
	 * @param terms
	 */
	public boolean deleteDocument(Term... terms) {
		try {
			indexWriter.deleteDocuments(terms); // Query... queries
			indexWriter.commit();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除全部索引
	 * 
	 * @throws Exception
	 */
	public boolean deleteAll() {
		try {
			indexWriter.deleteAll();
			indexWriter.commit();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getHighlighter() {
		return highlighter;
	}

	public void setHighlighter(String[] highlighter) {
		this.highlighter = highlighter;
	}
}