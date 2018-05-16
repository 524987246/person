package org.great.util.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class test2 {
	public static void testAnalzyer(Analyzer analyzer, String text) throws IOException {

		System.out.println("当前使用的分词器：" + analyzer.getClass().getSimpleName());
		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
		tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
			System.out.println(new String(charTermAttribute.toString()));
		}
	}

	public static void main(String[] args) throws Exception {
		String text = "name=2015年1季度国民经济开局平稳";
		Analyzer analyzer = new StandardAnalyzer();// 单字分词器
		testAnalzyer(analyzer, text);
		System.out.println("========");
//		analyzer = new IKAnalyzer(false);//
//		testAnalzyer(analyzer, text);
//		System.out.println("========");
//		analyzer = new IKAnalyzer(true);//
//		testAnalzyer(analyzer, text);
//		System.out.println("========");
		analyzer = new StopAnalyzer();
		testAnalzyer(analyzer, text);
		System.out.println("========");
		analyzer = new SimpleAnalyzer();
		testAnalzyer(analyzer, text);
		System.out.println("========");
		analyzer = new WhitespaceAnalyzer();
		testAnalzyer(analyzer, text);
		System.out.println("========");
		analyzer = new CJKAnalyzer();
		testAnalzyer(analyzer, text);
		System.out.println("========");
	}
}
