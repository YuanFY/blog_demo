package com.yuanfy.study.bean.depend;

public class TestA {
	private TestB testB;
	
	public void a(){
		System.out.println("a...");
		testB.b();
	}

	public TestB getTestB() {
		return testB;
	}

	public void setTestB(TestB testB) {
		this.testB = testB;
	}
	
	
}
