package com.yuanfy.test.thread;

public class PlateConsumer implements Runnable{
    PlateFactorys pf ;
    
    public PlateConsumer(PlateFactorys pf) {
        this.pf = pf;
    }

    @Override
    public void run() {
        pf.get();
    }

}
