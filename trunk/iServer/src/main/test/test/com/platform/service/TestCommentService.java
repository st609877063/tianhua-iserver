package test.com.platform.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platform.domain.Comment;
import com.platform.domain.Logs;
import com.platform.service.CommentService;
import com.platform.service.LogService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:/spring/applicationContext.xml","classpath:/spring/applicationContext-*.xml"})
public class TestCommentService extends
AbstractTransactionalJUnit4SpringContextTests  {
	@Autowired
	private CommentService commentService;
	
	Comment comment = new Comment();
	@Before
	public void prepareData(){
		System.err.println("Before Insert Comment...");
		
		//comment.setMagazineId("1");
		//comment.setUserId("1");
		comment.setContent("test");
		commentService.saveComment(comment);
	}
	@Test
    @Rollback(true) 
	public void handleSaveUser() {
		
		List l = commentService.getCommentsList(0);
		
		Assert.assertNotNull(l);
	}
}
