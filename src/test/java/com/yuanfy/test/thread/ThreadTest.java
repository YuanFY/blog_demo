package com.yuanfy.test.thread;

import org.junit.Test;

/**
 * @Description: 线程测试
 * @author yuanfy
 * @date 2018年1月23日 下午4:37:30 
 * @version 1.0
 */
public class ThreadTest {
    @Test
    public void test(){
        PlateFactorys f = new PlateFactorys();
        PlateProducter producter = new PlateProducter(f);
        PlateConsumer consumer = new PlateConsumer(f);
        new Thread(producter).start();
        new Thread(consumer).start();
    }
}
