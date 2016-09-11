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
		// QueryResult<Timu> qr = timuSearchService.query("全国统考理综物理", 0, 5);
		/**做全文检索报错：
		 * org.compass.core.engine.SearchEngineQueryParseException: Failed to parse query [1、若集合M=红(+4XX+D:0,N:{X(4xX—D=0},则M∩N:()]; nested exception is org.apache.lucene.queryParser.ParseException: Cannot parse '1、若集合M=红(+4XX+D:0,N:{X(4xX—D=0},则M∩N:()': Encountered " ":" ": "" at line 1, column 19.
			Was expecting one of: <AND> ... <OR> ... <NOT> ... "+" ... "-" ... "(" ... ")" ... "*" ... "^" ... <QUOTED> ... <TERM> ... <FUZZY_SLOP> ... <PREFIXTERM> ... <WILDTERM> ... "[" ... "{" ... <NUMBER> ...  
		 
		 *	解决方案：通过：String queryString = QueryParser.escape( keyword );将关键词中的特殊字符进行转义；
		 *	为了统一处理，将该步骤过程放到QueryCallback中进行。
		 */
		QueryResult<Timu> qr = timuSearchService.query("后期 物理有何感想1、若集合M=红(+4XX+D:0,N:{X(4xX—D=0},则M∩N:()", 0, 30);
		for(Timu timu : qr.getResultlist()){
			System.out.println(timu.toString());			
		}
		System.out.println("\n>>>匹配总记录数："+qr.getTotalrecord());
	}

}
