package junit.test;

import org.apache.lucene.queryParser.QueryParser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tilisou.beans.Timu;
import com.tilisou.service.ITimuSearchService;
import com.tilisou.viewmodel.QueryResult;

public class TimuSearchServiceTest {
	private static ITimuSearchService timuSearchService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			timuSearchService = (ITimuSearchService)cxt.getBean("timuSearchServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void testQuery() {
		// QueryResult<Timu> qr = timuSearchService.query("ȫ��ͳ����������", 0, 5);
		/**��ȫ�ļ�������
		 * org.compass.core.engine.SearchEngineQueryParseException: Failed to parse query [1��������M=��(+4XX+D:0,N:{X(4xX��D=0},��M��N:()]; nested exception is org.apache.lucene.queryParser.ParseException: Cannot parse '1��������M=��(+4XX+D:0,N:{X(4xX��D=0},��M��N:()': Encountered " ":" ": "" at line 1, column 19.
			Was expecting one of: <AND> ... <OR> ... <NOT> ... "+" ... "-" ... "(" ... ")" ... "*" ... "^" ... <QUOTED> ... <TERM> ... <FUZZY_SLOP> ... <PREFIXTERM> ... <WILDTERM> ... "[" ... "{" ... <NUMBER> ...  
		 
		 *	���������ͨ����String queryString = QueryParser.escape( keyword );���ؼ����е������ַ�����ת�壻
		 *	Ϊ��ͳһ�������ò�����̷ŵ�QueryCallback�н��С�
		 */
		QueryResult<Timu> qr = timuSearchService.query("���� �����кθ���1��������M=��(+4XX+D:0,N:{X(4xX��D=0},��M��N:()", 0, 30);
		for(Timu timu : qr.getResultlist()){
			System.out.println(timu.toString());			
		}
		System.out.println("\n>>>ƥ���ܼ�¼����"+qr.getTotalrecord());
	}

}
