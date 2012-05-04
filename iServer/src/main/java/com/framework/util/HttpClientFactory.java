package com.framework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientFactory {
	public static DefaultHttpClient getHttpClient() {
		SchemeRegistry s = new SchemeRegistry();
		s.register(new Scheme(ICloud.HOST_PROTOCAL, PlainSocketFactory
				.getSocketFactory(), ICloud.HOST_PORT));
		HttpParams p = new BasicHttpParams();
		ClientConnectionManager cm = new ThreadSafeClientConnManager(p, s);
		DefaultHttpClient httpclient = new DefaultHttpClient(cm, p);
		try {
			if (!authenticate(httpclient)) {
				System.out.println("Authentication failed!");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpclient;

	}

	public static boolean authenticate(DefaultHttpClient httpclient)
			throws ClientProtocolException, IOException {
		boolean ret;
		// ------ authenticate : POST /auth/login
		HttpPost httppost = new HttpPost("http://" + ICloud.HOST_NAME
				+ "/csk/api/auth/login");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user", ICloud.USER_NAME));
		nvps.add(new BasicNameValuePair("pass", ICloud.USER_PASS));
		httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		System.out.println("Executing request: " + httppost.getRequestLine());
		System.out.println("----------------------------------------");
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());
		if (response.getStatusLine().getStatusCode() == 200) {
			BasicClientCookie cookie = new BasicClientCookie("savedUserId",
					ICloud.USER_NAME);
			cookie.setDomain(ICloud.HOST_DOMAIN);
			cookie.setPath("/csk");
			httpclient.getCookieStore().addCookie(cookie);
			ret = true;
		} else {
			ret = false;
		}
		if (resEntity != null) {
			System.out.println("Response content length: "
					+ resEntity.getContentLength());
			System.out.println(EntityUtils.toString(resEntity));
		} else {
			System.out.println("Response Body is empty.\n");
		}
		System.out.println("----------------------------------------");

		return ret;
	}
}
