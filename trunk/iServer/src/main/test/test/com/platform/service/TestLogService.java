package test.com.platform.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platform.domain.Logs;
import com.platform.service.LogService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:/spring/applicationContext.xml","classpath:/spring/applicationContext-*.xml"})
public class TestLogService extends
AbstractTransactionalJUnit4SpringContextTests  {
	@Autowired
	private LogService logService;
	
	Logs log = new Logs();

	@Before
	public void prepareData(){
		System.err.println("Before Insert...");
	}
	@Test
    @Rollback(false) 
	public void handleSaveUser() {
		String str = logService.getLogs();
		Assert.assertNotNull(str);
	}
}
