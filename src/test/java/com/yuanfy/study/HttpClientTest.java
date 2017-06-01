package com.yuanfy.study;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @description 测试HttpClient
 * @author YuanFY 
 * @date 2017年4月22日 下午9:14:50
 * @version 1.0
 */
public class HttpClientTest {

	@Test
	public void testSimple() throws Exception{
		//1、创建默认HttpClient对象
		@SuppressWarnings({ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		
		//2、创建请求对象
		HttpGet httpGet = new HttpGet("http://localhost:8080/monitor-site/index.html");
		
		//3、执行请求,返回响应对象,获取响应体
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		
		System.out.println( entity.getContentType() 
				+ ", content-length:" + entity.getContentLength());
		
		//System.out.println(EntityUtils.toString(entity));
		
		//4、打印响应体内容
		/*if (entity != null) {
			InputStream is = entity.getContent();
			int len = 0;
			byte[] tmp = new byte[1024];
			StringBuffer result = new StringBuffer();
			while ((len = is.read(tmp)) != -1) {
				result.append(new String(tmp, 0, len));
			}
			System.out.println("响应体内容："+result.toString());
			is.close();
		}*/
		System.out.println(EntityUtils.toString(entity));
		
	}
	
	@Test
	public void testURI() throws Exception{
		HttpGet httpget = new HttpGet(
				"http://www.google.com/search?hl=en&q=httpclient&btnG=GoogleSearch&aq=f&oq=");
		System.out.println(httpget.getURI());
		
		URI uri = URIUtils.createURI("http", "www.google.com", -1, "/search", "hl=en&q=httpclient&btnG=GoogleSearch&aq=f&oq=", null);
		httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("q", "httpclient"));
		qparams.add(new BasicNameValuePair("btnG", "GoogleSearch"));
		qparams.add(new BasicNameValuePair("aq", "f"));
		qparams.add(new BasicNameValuePair("oq", "null"));
		
		uri = URIUtils.createURI("http", "www.google.com", -1, "/search", 
				URLEncodedUtils.format(qparams, "UTF-8"), null);
		System.out.println(uri);
		
	}
	
	@Test
	public void testResponse() throws Exception{
		HttpResponse response =  new BasicHttpResponse(
				HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}
	
	@Test
	public void testHeader() throws Exception{
		HttpResponse response = new BasicHttpResponse(
				HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		
		response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
		response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
		
		/*Header h1 = response.getFirstHeader("Set-Cookie");
		System.out.println(h1);
		Header h2 = response.getLastHeader("Set-Cookie");
		System.out.println(h2);
		Header[] hs = response.getHeaders("Set-Cookie");
		System.out.println(hs.length);*/
		
		HeaderIterator iterator = response.headerIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		BasicHeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator());
		while (it.hasNext()) {
			HeaderElement e = it.nextElement();
			System.out.println(e.getName() + " = " + e.getValue());
			
			/*NameValuePair[] params = e.getParameters();
			for (int i = 0; i < params.length; i ++) {
				System.out.println(" " + params[i]);
			}*/
			
		}
	}
	
	@Test
	public void testTarget() throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpContext httpContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		HttpResponse response = httpClient.execute(httpGet, httpContext);
		
		HttpHost target = (HttpHost)httpContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		System.out.println(target);
	}
}
