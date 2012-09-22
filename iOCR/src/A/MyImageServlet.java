package A;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>Class Name£ºMyServlet.java<b>
 * 
 * <pre>
 * Procedure function is described£º
 * 		&lt;b&gt;MyServlet&lt;/b&gt;
 * </pre>
 * 
 * <pre>
 *  Demo: 
 * 	Bug: 
 * 
 * 	Procedure modify date £º
 * 	modify author £º
 * 	modify explanation £º
 * </pre>
 * 
 * @version 1.0
 * @since 2011-3-28
 * @author springmvc2006@sina.com
 */
public class MyImageServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		String uuid = request.getParameter("uuid");
		byte[] b = (byte[])ImageData.imags.get(uuid);	
		response.setContentType("text/html; charset=utf-8");
		response.setContentType("image/jpeg"); 
		response.getOutputStream().write(b);
		response.getOutputStream().flush();
		
		ImageData.imags.remove(uuid);
		

		
		} catch (Exception e) {
			
	    }
	}
	
	
	
}
