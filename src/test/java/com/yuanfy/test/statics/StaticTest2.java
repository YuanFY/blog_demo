package com.yuanfy.test.statics;
//导入多个静态包
import static com.yuanfy.test.StringUtil.*;
import static com.yuanfy.test.scope2.StringUtil.*;
public class StaticTest2 {
	
	public static void main(String[] args) {
		String str1 = "123";
		String str2 = new String("123");
		//编译报错，The method isEmpty(String) is ambiguous for the type StaticTest2
		/*if (isEmpty(str1)){
			
		}*/
	}
}
