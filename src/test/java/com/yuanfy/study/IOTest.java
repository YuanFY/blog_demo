package com.yuanfy.study;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class IOTest {

	@Test
	public void test() throws IOException{
		File file = new File("F:/ip addr.txt");
		FileInputStream fis = new FileInputStream(file); 
		InputStreamReader reader = new InputStreamReader(fis, Charset.forName("utf-8"));
		
		char[] b = new char[1024];
//		byte[] b = new byte[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		
		
		while ((len = reader.read(b)) != -1){
			sb.append(new String(b, 0, len));
		}
		
		System.out.println(sb.toString());
		System.out.println(System.getProperty("file.encoding"));
		
		ClassPathResource
	}
}
