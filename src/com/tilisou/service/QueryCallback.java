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
 * ͨ���ִʼ��������������ݼ�¼
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����3:22:55
 * 
 */
public class QueryCallback implements CompassCallback<QueryResult<Timu>> {
	private String keyword;// �ؼ���
	private int firstResult;// ��ʼ��¼λ��
	private int maxResult;// ��ѯ����¼��

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
		 * ��ѯָ������ƥ���¼������pubDate�������� sql: (xxxx like ?) order by pubDate desc
		 */
		CompassQueryBuilder queryBuilder = session.queryBuilder();
		
		/*// ָ����ѯʵ��
		CompassQuery queryAlias = queryBuilder.alias(Timu.class.getSimpleName());
		*/
		CompassBooleanQueryBuilder boolQueryBuilder = queryBuilder.bool();
		/*
		boolQueryBuilder.addMust(queryAlias);
		*/
		
		//�������ڶԼ����ؼ��ʽ��зִ��и�ķִʷ�����
        Analyzer luceneAnalyzer = new PaodingAnalyzer();
        
        // ���Թ� Query ���� ת��ΪCompassQuery�� ���Բ�����
		/*try {			
		 	// QueryParser  Lucene��ѯ������ �������queryStr����ɸ��Ӳ���	
			System.out.println((new QueryParser("", luceneAnalyzer).parse(keyword)).toString());
		} catch (ParseException e) {						
			e.printStackTrace();
			return qr;
		}*/
		
        // ��������ͨ���ִʺ�õ���Ŀƴ�ӳ��µļ����ؼ��ʴ�
        TokenStream tokenStream=null;
        Token token = new Token();
        StringBuffer fenciBuffer = new StringBuffer(keyword);
		tokenStream=luceneAnalyzer.tokenStream("", new StringReader(keyword));
		try {
			// �õ��ִ���Ŀƴ�ӳ��µļ����ؼ��ʴ�
			while ((token = tokenStream.next(token)) != null) {
			   fenciBuffer.append(" "+token.term());
			}
		} catch (IOException e) {
			// �����쳣��ֱ�ӷ���
			e.printStackTrace();
			return qr;
		}
		// System.out.println(fenciBuffer.toString());
		
        // ͨ��QueryParser.escape()�����Բ�ѯ�ؼ����е������ַ�����ת��
		String[] array = fenciBuffer.toString().split(" ");
		
		//ͨ����ּ�����������ø���׼�Ĳ���ƥ��ȫ�ļ�����ѯ
		if (array != null && array.length > 0) {
			for (String value : array) {
				if (value != null && StringUtils.isNotEmpty(value.trim())) {
					// System.out.println(QueryParser.escape(value));	
					//����ȫ����ȫƥ��
					// boolQueryBuilder.addMust(queryBuilder.queryString(QueryParser.escape(value)).toQuery());
					//����ƥ�� Ҳ��û��
					boolQueryBuilder.addShould(queryBuilder.queryString( QueryParser.escape(value)).toQuery());
				}
			}
		}
		
		/*String queryString = QueryParser.escape( keyword );
		System.out.println(">>QueryParser.escape( keyword )��Ĳ�ѯ�ؼ��ı���"+queryString);*/
		/* �򵥹ؼ��ʲ�ѯ */
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
		
		// ��ȡƥ���¼������
		qr.setTotalrecord(hits.length());
		// ���÷��ؽ����
		qr.setResultlist(timus);
		
		return qr;
	}

}
