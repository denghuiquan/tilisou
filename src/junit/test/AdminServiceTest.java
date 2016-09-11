package junit.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.tilisou.beans.Admin;
import com.tilisou.service.IAdminService;
import com.tilisou.utils.MD5;

public class AdminServiceTest {

	private static IAdminService iAdminService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			iAdminService = (IAdminService) ctx.getBean("adminServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		Admin manager = new Admin();
		manager.setName("tuoling");
		manager.setPassword("123456");
		manager.setEmail("tuoling@qq.com");
		manager.setCountlogin(1);
		manager.setLastLogindate(new Date());
		iAdminService.save(manager);
		Assert.assertTrue(iAdminService.exsit("tuoling"));
		Assert.assertEquals(5, iAdminService.getCount());
	}

	@Test
	public void testUpDatepsw() {
		iAdminService.updatePassword("DenMark", MD5.MD5Encode("111111"));
		Assert.assertTrue(iAdminService.validate("DenMark", MD5.MD5Encode("111111")));
	}

}
