package com.yuanfy.study.bean.depend;

public class TestC {
	private TestA testA;
	
	public void c(){
		System.out.println("c...");
		testA.a();
	}

	public TestA getTestA() {
		return testA;
	}

	public void setTestA(TestA testA) {
		this.testA = testA;
	}
}
