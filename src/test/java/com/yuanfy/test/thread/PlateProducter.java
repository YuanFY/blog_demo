package com.yuanfy.test.thread;

public class PlateProducter implements Runnable{
    PlateFactorys pf ;
    
    public PlateProducter(PlateFactorys pf) {
        this.pf = pf;
    }

    @Override
    public void run() {
        pf.put();
    }

}
