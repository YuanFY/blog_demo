package com.yuanfy.test.thread.producerAndConsumerModel1;

public class Consumer implements Runnable{

	PlateFactory pf;
	public Consumer(PlateFactory pf) {
		this.pf = pf;
	}
	
	@Override
	public void run() {
		while(true) {
			this.pf.get();
		}
	}
}
