package com.tilisou.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.queryParser.QueryParser;
import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassQueryBuilder.CompassBooleanQueryBuilder;
import org.compass.core.CompassSession;
import com.tilisou.beans.Timu;
import com.tilisou.viewmodel.QueryResult;

/**
 * 通过分词技术检索索引数据记录
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 下午3:22:55
 * 
 */
public class QueryCallback implements CompassCallback<QueryResult<Timu>> {
	private String keyword;// 关键词
	private int firstResult;// 开始记录位置
	private int maxResult;// 查询最大记录数

	public QueryCallback(String keyword, int firstResult, int maxResult) {
		this.firstResult = firstResult;
		this.maxResult = maxResult;
		this.keyword = keyword;
	}

	public QueryResult<Timu> doInCompass(CompassSession session)
			throws CompassException {

		QueryResult<Timu> qr = new QueryResult<Timu>();
		if (keyword == null || "".equals(keyword)) {
			return qr;
		}
		
		/*
		 * 查询指定类别的匹配记录，并按pubDate降序排序 sql: (xxxx like ?) order by pubDate desc
		 */
		CompassQueryBuilder queryBuilder = session.queryBuilder();
		
		/*// 指定查询实体
		CompassQuery queryAlias = queryBuilder.alias(Timu.class.getSimpleName());
		*/
		CompassBooleanQueryBuilder boolQueryBuilder = queryBuilder.bool();
		/*
		boolQueryBuilder.addMust(queryAlias);
		*/
		
		//创键用于对检索关键词进行分词切割的分词分析器
        Analyzer luceneAnalyzer = new PaodingAnalyzer();
        
        // 尝试过 Query 不能 转换为CompassQuery， 所以不能用
		/*try {			
		 	// QueryParser  Lucene查询分析器 任意组合queryStr，完成复杂操作	
			System.out.println((new QueryParser("", luceneAnalyzer).parse(keyword)).toString());
		} catch (ParseException e) {						
			e.printStackTrace();
			return qr;
		}*/
		
        // 换方案，通过分词后得到条目拼接成新的检索关键词串
        TokenStream tokenStream=null;
        Token token = new Token();
        StringBuffer fenciBuffer = new StringBuffer(keyword);
		tokenStream=luceneAnalyzer.tokenStream("", new StringReader(keyword));
		try {
			// 得到分词条目拼接成新的检索关键词串
			while ((token = tokenStream.next(token)) != null) {
			   fenciBuffer.append(" "+token.term());
			}
		} catch (IOException e) {
			// 发生异常，直接返回
			e.printStackTrace();
			return qr;
		}
		// System.out.println(fenciBuffer.toString());
		
        // 通过QueryParser.escape()方法对查询关键词中的特殊字符进行转义
		String[] array = fenciBuffer.toString().split(" ");
		
		//通过查分检索词条，获得更精准的部分匹配全文检索查询
		if (array != null && array.length > 0) {
			for (String value : array) {
				if (value != null && StringUtils.isNotEmpty(value.trim())) {
					// System.out.println(QueryParser.escape(value));	
					//必须全部完全匹配
					// boolQueryBuilder.addMust(queryBuilder.queryString(QueryParser.escape(value)).toQuery());
					//或者匹配 也许没有
					boolQueryBuilder.addShould(queryBuilder.queryString( QueryParser.escape(value)).toQuery());
				}
			}
		}
		
		/*String queryString = QueryParser.escape( keyword );
		System.out.println(">>QueryParser.escape( keyword )后的查询关键文本："+queryString);*/
		/* 简单关键词查询 */
		// CompassHits hits = session.find(queryString);
		
		CompassHits hits = /*queryBuilder
				.bool()
				.addMust(queryBuilder.queryString(queryString).toQuery())*/
				boolQueryBuilder.toQuery()
				/*.addSort("pubDate", SortPropertyType.STRING,
						SortDirection.REVERSE)*/.hits();
		
		int length = firstResult + maxResult;
		if (length > hits.length())
			length = hits.length();
		List<Timu> timus = new ArrayList<Timu>();
		for (int i = firstResult; i < length; i++) {
			Timu timu = (Timu) hits.data(i);
			if (hits.highlighter(i).fragment("problemContentText") != null)
				timu.setProblemContentText(hits.highlighter(i).fragment(
						"problemContentText"));
			if (hits.highlighter(i).fragment("proSourceDesc") != null)
				timu.setProSourceDesc(hits.highlighter(i).fragment(
						"proSourceDesc"));
			if (hits.highlighter(i).fragment("type") != null)
				timu.setType(hits.highlighter(i).fragment("type"));
			/*if (hits.highlighter(i).fragment("grade") != null)
				timu.setGrade(hits.highlighter(i).fragment("grade"));
			if (hits.highlighter(i).fragment("subject") != null)
				timu.setSubject(hits.highlighter(i)
						.fragment("subject"));*/
			timus.add(timu);
		}
		
		// 获取匹配记录的总数
		qr.setTotalrecord(hits.length());
		// 设置返回结果集
		qr.setResultlist(timus);
		
		return qr;
	}

}
