package com.yuanfy.study;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * 
 * @description 测试HttpUrlCon
 * @author YuanFY 
 * @date 2017年4月22日 下午3:28:07
 * @version 1.0
 */
public class HttpURLConnectionTest {
	
	/**
	 * @description 测试HttpURLConnection请求一个url获取响应体。 
	 * @throws IOException
	 * @author YuanFY
	 * @date 2017年4月22日 下午4:37:45
	 * @version 1.0
	 */
	@Test
	public void testHttpUrlConnection() throws IOException{
		//1、定义url对象
		URL url = new URL("http://localhost:8080/monitor-site/getNameById.html");
		
		//2、打开连接
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
		
		//3、设置参数
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoOutput(true);//是否使用httpurl进行输出
		httpURLConnection.setDoInput(true);
		httpURLConnection.setUseCaches(false);//取消缓存
		// 设定传送的内容类型是可序列化的java对象    
		// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)    
		httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
		
		//4、建立连接
		//httpURLConnection.connect();
		
		//写入数据
		OutputStream outputStream = httpURLConnection.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream);
		oos.writeInt(123);
		oos.flush();// flush输出流的缓冲  
		 
		
		//获取返回内容
		InputStream os = httpURLConnection.getInputStream();  
		StringBuffer result = new StringBuffer();
		byte[] bytes = new byte[1024];
		int r = 0;
		while ((r = os.read(bytes)) != -1){
			result.append(new String(bytes, 0, r));
		}
		System.out.println(result.toString());
		
		
		System.out.println(httpURLConnection.getResponseCode());
        os.close();  
        httpURLConnection.disconnect();  
	}
}
