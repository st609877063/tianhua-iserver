/**
 * SmsUtil.java
 * description: 
 * dptwb
 * author: mayq
 * 2013-5-27
 */
package com.people.dptwb.tools;

import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 短信相关的辅助类
 * Description: 
 * @author mayq
 * 2013-5-27
 */
public class SmsUtil {
	private static final Logger logger = Logger.getLogger(SmsUtil.class);
	
	
	public static final String SMS_VALIDCODE_PRECONTENT = "欢迎使用人民微管家，您的短信密码为 ：";
	
	public static final String SMS_AUTOGEN_PASSWORD_PREFIX_CONTENT = "您重置的密码为：";
	public static final String SMS_AUTOGEN_PASSWORD_SUFFIX_CONTENT = "，人民微管家";
	
	public static final String SMS_SIGNUSER_NOTICE_CONTENT = "尊敬的用户（1341231xxxx），您已被手机尾号为xxxx的（用户昵称）设置为xx微博的签发人/送签人。您可以用手机号登陆z.people.com.cn对微博进行管理。【人民微管家】";
	
	
	/**
	 * 调用短信服务程序接口，向指定的手机号发送短信
	 * @param mobile
	 * @param content
	 * @return 1：接口调用成功；0：接口调用失败
	 */
	public static synchronized int sendContentSmsToMobile(String mobile, String content) {
		int reVal = 0;
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpPost httppost = new HttpPost("http://10.100.7.208:9172/genMtMessage");

			StringBody comment = new StringBody(content, Charset.forName("GBK"));

			MultipartEntity reqEntity = new MultipartEntity();

			reqEntity.addPart("SrcMobile", new StringBody(mobile));
			reqEntity.addPart("Content", comment);
			reqEntity.addPart("SrcType", new StringBody("5"));

			httppost.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();

			logger.info("短信平台下发接口返回状态：" + response.getStatusLine());
			if (resEntity != null) {
				String re = EntityUtils.toString(resEntity);
				logger.info("短信平台下发接口返回值：" + re);
				if ("OK".equals(re)) {
					reVal = 1;
				}
			}
			if (resEntity != null) {
				EntityUtils.consume(resEntity);
			}

		} catch (Exception e) {
			logger.error("调用短信平台下发接口时出现异常", e);
		}
		finally {
			httpclient.getConnectionManager().shutdown();
		}
		return reVal;
	}
	

}
