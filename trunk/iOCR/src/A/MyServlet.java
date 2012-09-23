package A;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.id.uuid.UUID;

import com.overseas.domain.TesseractEntity;
import com.overseas.ocr.AnalysePictureEx;
import com.overseas.ocr.ImageFilter;
import com.overseas.ocr.ImageIOHelper;
import com.overseas.sys.InitEnv;
import com.overseas.util.PicUtil;

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
public class MyServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String url = req.getParameter("j_imgurl");
		//http://passport.people.com.cn/imageCode.do
		System.out.println("url=============="+url);
		InputStream instream = InitEnv.class.getResourceAsStream(InitEnv.CERTPATH);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String protocol = "http";
		byte[] b = null;
		try {
			if(url.startsWith("https")){
				protocol = "https";
			}
			PicUtil.getPic(protocol,url,InitEnv.PORT, InitEnv.PWD, instream, outputStream);
			b = outputStream.toByteArray();
			ImageFilter imageFilter = new ImageFilter(new ByteArrayInputStream(b));
			outputStream.close();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();	
			ImageIOHelper.createImage(imageFilter.changeGrey(), byteArrayOutputStream);
			byte[] imgData = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.close();
			
			if(imgData == null || imgData.length <=0){
				return ;
			}

			TesseractEntity tesseractEntity =new TesseractEntity();
			tesseractEntity.setImgByte(imgData);
			tesseractEntity.setIdentifyingCode("-1");
			tesseractEntity.setLength(imgData.length);
			tesseractEntity.setPath(InitEnv.unZipOcrPath);
			tesseractEntity.setLang_option("eng");
			
			String result = AnalysePictureEx.analysePicEx(tesseractEntity);
			String uuid = UUID.randomUUID().toString();
			ImageData.imags.put(uuid, b );
			
			req.setAttribute("identifyingCode", tesseractEntity.getIdentifyingCode());
			req.setAttribute("uuid", uuid);
	        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/result.jsp");
	        requestDispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
