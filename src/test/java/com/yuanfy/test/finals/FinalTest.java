package com.yuanfy.test.finals;

class MyClass{
    final void test(){
        System.out.println("FinalClass");
    }
}
class MyClass2 extends MyClass {
    //编译报错：Cannot override the final method from MyClass
//    public void test(){
//        System.out.println("FinalClass");
//    }
}

class Count {
    int count = 0;
    public int getCount () {
        return ++ count;
    }
}

public class FinalTest {
    int num = 0;
    public static void main(String[] args)  {
        final Count count = new Count();
        addCount(count);
        System.out.println(count.count);
    }
    public static void addCount(final Count count){
        count.getCount();
        //count = new Count();//这种就是篡改。
    }
}

/*String a = "hello2"; 
final String b = "hello";
final String c = getHello();
String e = "hello";

String f = b + 2; 
String g = c + 2;
String h = e + 2;
System.out.println((a == f));
System.out.println((a == g));
System.out.println((a == h));

public static String getHello(){
        return "hello";
    }*/
