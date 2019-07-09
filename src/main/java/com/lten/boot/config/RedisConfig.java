package com.lten.boot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/4/11 16:49
 */
@Configuration
@Data
public class RedisConfig {

    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxMiddle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minMiddle;

    @Value("${spring.redis.timeout}")
    private String timeOut;

    private RedisConnectionFactory redisConnectionFactory = null;

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxMiddle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMinIdle(minMiddle);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //获取redis的状态：单机、哨兵、集群，进行设置
//        RedisClusterConfiguration clusterConfiguration = connectionFactory.getClusterConfiguration();
        return this.redisConnectionFactory = connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }

}
