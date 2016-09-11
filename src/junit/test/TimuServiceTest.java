package junit.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tilisou.beans.Grade;
import com.tilisou.beans.Subject;
import com.tilisou.beans.Timu;
import com.tilisou.service.ITimuService;
import com.tilisou.utils.DateFormatutil;

public class TimuServiceTest {
	private static ITimuService timuService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx 
				= new ClassPathXmlApplicationContext("applicationContext.xml");
			timuService = (ITimuService)ctx.getBean("timuServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSave() {
		/**
			 * 5	2015 ��ȫ��ͳ����������	Ӧ����	1��������M=��(+4XX+D:0,N:{X(4xX��D=0},��M��N:() A{14} B.{��14} C{0} D�� 	/timusDir/2015/0924/dd082b82-c3c6-4ed4-8b5d-632867ebf5fc.jpg	/timusDir/2015/0924/a_dd082b82-c3c6-4ed4-8b5d-632867ebf5fc.jpg	2015-09-24 21:44:56	��һ	����
			 * 6	2015 ��ȫ��ͳ����������	Ӧ����	�������ģ���ˢ�²����ύ�����ģ�������Ҫ���п��ơ�	/timusDir/2015/0924/2afe5d95-36d9-4367-890a-64f10227c210.jpg	/timusDir/2015/0924/a_2afe5d95-36d9-4367-890a-64f10227c210.jpg	2015-09-24 21:47:28	��һ	����
			 * 
			 * */
		Timu timu = new Timu();
		for(int i=0;i<10;i++){
			timu.settId(null);
			timu.setType("�����");
			timu.setProSourceDesc("2015��㶫ͳ����������");
			timu.setProblemContentText("1��������M=��(+4XX+D:0,N:{X(4xX��D=0},��M��N:() A{14} B.{��14} C{0} D��");
			timu.setqPicUrl("/timusDir/2015/0924/dd082b82-c3c6-4ed4-8b5d-632867ebf5fc.jpg");
			timu.setaPicUrl("/timusDir/2015/0924/a_dd082b82-c3c6-4ed4-8b5d-632867ebf5fc.jpg");
			timu.setPubDate(DateFormatutil.getDate(new Date()));
			timu.setGrade(Grade.G_2);
			timu.setSubject(Subject.DL);
			timuService.save(timu);
			System.out.println(timu.gettId());			
		}		
	}
	
	@Test
	public void testUpdate() {
		for(int i=1;i<=70;i++){
			Timu timu=timuService.find(i);
			if (timu != null) {
				timu.setqPicUrl("/pic/test/pic05.jpg");
				timu.setaPicUrl("/pic/test/pic05.jpg");
				timuService.update(timu);
				System.out.println(timu.toString());
			}		
		}
	}
	
	@Test
	public void testDelete() {
		int count=(int) timuService.getCount();
		Integer[] ids=new Integer[30];
		for (int i = 0; i < 30; i++) {
			ids[i]=i+21;
		}
		System.out.println(count);
		timuService.delete(ids);
		
		Assert.assertEquals(0, timuService.getCount());
	}
}
