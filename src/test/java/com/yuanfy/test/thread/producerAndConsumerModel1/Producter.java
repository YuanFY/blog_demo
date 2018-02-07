package com.yuanfy.test.thread.producerAndConsumerModel1;

public class Producter implements Runnable{

	
	PlateFactory pf;
	public Producter(PlateFactory pf) {
		this.pf = pf;
	}
	
	@Override
	public void run() {
		while(true){
			this.pf.put();
		}
	}
}
