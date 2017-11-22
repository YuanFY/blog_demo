package com.yuanfy.study.bean.depend;

public class TestB {
	private TestC testC;
	
	public void b(){
		System.out.println("b...");
		testC.c();
	}

	public TestC getTestC() {
		return testC;
	}

	public void setTestC(TestC testC) {
		this.testC = testC;
	}
}
