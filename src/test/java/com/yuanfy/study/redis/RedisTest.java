package com.yuanfy.study.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {
        
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());
        System.out.println(jedis.getClient().getPort());
        
        jedis.lpush("name", "1231", "1232");
        
    }
}
