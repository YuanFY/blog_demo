package com.yuanfy.test.finals;

public class FinalTest2 {

    public static void main(String[] args) {
        final String str1 = "test";
        final String str2 = getContent();
        final String str3 = new String("test");
        String str = "test";
        
        String str4 = str1 + "";
        String str5 = str2 + "";
        String str6 = str3 + "";
        
        System.out.println(str == str4);
        System.out.println(str == str5);
        System.out.println(str == str6);
    }
    public static String getContent(){
        return "test";
    }
}
