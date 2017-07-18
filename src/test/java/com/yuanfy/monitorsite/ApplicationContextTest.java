package com.yuanfy.monitorsite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)   
@WebAppConfiguration  
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class ApplicationContextTest {
    @Autowired  
    private WebApplicationContext wac;  
  
    @Test
    public void test(){
        System.out.println(this.wac);
    }
    
}
