package com.lten.boot;

import com.lten.boot.config.MailProperties;
import com.lten.boot.config.RedisConfig;
import com.lten.boot.mapper.UserMapper;
import com.lten.boot.pojo.User;
import com.lten.boot.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootMybatisAnnoApplicationTests {

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private RedisConfig redisConfig;

    @Test
    public void springObj() {

    }

    @Test
    public void redisSentinel(){
        //测试哨兵
        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("192.168.58.100",26379).toString());
        sentinels.add(new HostAndPort("192.168.58.100",26380).toString());
        sentinels.add(new HostAndPort("192.168.58.100",26381).toString());

        JedisSentinelPool jedisSentinelPool= new JedisSentinelPool("mymaster",sentinels);
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("course","语文");
    }

    @Test
    public void redisShard() {
        //多台redis服务器分片测试
        List<JedisShardInfo> list = new ArrayList<>();
        list.add(new JedisShardInfo("192.168.58.100",6379));
        list.add(new JedisShardInfo("192.168.58.100",6380));
        list.add(new JedisShardInfo("192.168.58.100",6381));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);

        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,list);
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set("name","lijinbao");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void redisTest() {
        //单机版测试
        System.out.println(redisConfig.toString());
        Jedis jedis = new Jedis("192.168.58.100",6379);
        jedis.set("name","你弄啥嘞");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void mybatisCacheTest_One(){



        /***
         *                    .::::.
         *                  .::::::::.
         *                 :::::::::::  COME ON BABY!
         *             ..:::::::::::'
         *           '::::::::::::'
         *             .::::::::::
         *        '::::::::::::::..
         *             ..::::::::::::.
         *           ``::::::::::::::::.
         *            ::::``:::::::::'        .:::.
         *           ::::'   ':::::'       .::::::::.
         *         .::::'      ::::     .:::::::'::::.
         *        .:::'       :::::  .:::::::::' ':::::.
         *       .::'        :::::.:::::::::'      ':::::.
         *      .::'         ::::::::::::::'         ``::::.
         *  ...:::           ::::::::::::'              ``::.
         * ```` ':.          ':::::::::'                  ::::..
         *                    '.:::::'                    ':'````..
         */

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        System.out.println(mapper1.findAll());
        sqlSession1.close();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        System.out.println(mapper2.findAll());
        sqlSession2.close();
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        System.out.println(mapper3.findAll());
        sqlSession3.close();

        /***
         *           _.._        ,------------.
         *        ,'      `.    (   GET OUT ! )
         *       /  __) __` \    `-,----------'
         *      (  (`-`(-')  ) _.-'
         *      /)  \  = /  (
         *     /'    |--' .  \
         *    (  ,---|  `-.)__`
         *     )(  `-.,--'   _`-.
         *    '/,'          (  Uu",
         *     (_       ,    `/,-' )
         *     `.__,  : `-'/  /`--'
         *       |     `--'  |
         *       `   `-._   /
         *        \        (
         *        /\ .      \.
         *       / |` \     ,-\
         *      /  \| .)   /   \
         *     ( ,'|\    ,'     :
         *     | \,`.`--"/      }
         *     `,'    \  |,'    /
         *    / "-._   `-/      |
         *    "-.   "-.,'|     ;
         *   /        _/["---'""]
         *  :        /  |"-     '
         *  '           |      /
         *              `      |
         */
    }


    @Test
    public void addUser() {
        User user = new User();
        user.setId(null);
        user.setName("李逵");
        user.setPhone("57878794");
        userService.addUser(user);
    }

    @Test
    public void findAllUser() {
        List<User> allUser = userService.findAll();
        allUser.forEach(x -> System.out.println(x.toString()));
        System.out.println("----------------------------------");
        List<User> allUser1 = userService.findAll();
        allUser1.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void test() {
        System.out.println(mailProperties.getHost());
        System.out.println(mailProperties.getPort());
        System.out.println(mailProperties.getSmtpAuth());
        System.out.println(mailProperties.getSmtpStarttlsEnable());
        System.out.println(mailProperties.getMailFrom());
        System.out.println(mailProperties.getUsername());
        System.out.println(mailProperties.getPassword());
    }

    @Test
    public void test1() {
        long time = 4210156;
        System.out.println(time/1000.0);
        System.out.println(time%1000/100);
        System.out.println(time%1000%100/10);
        System.out.println(time%1000%100%10);
        double d = 11.1;
        long t = 11;
        System.out.println(d > t);
    }

    @Test
    public void changeIfElse() {
        String handle = userService.handle("1");
        System.out.println(handle);
    }

}
