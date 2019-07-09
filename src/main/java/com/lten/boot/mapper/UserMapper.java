package com.lten.boot.mapper;

import com.lten.boot.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.decorators.FifoCache;

import java.util.List;

/**
 * @author lijinbao
 * @data   2019/3/7
 */
@Mapper
@CacheNamespace(eviction = FifoCache.class,flushInterval = 60000,size = 1024,readWrite = true)
//声明自定义缓存
//@CacheNamespace(implementation = MybatisCache.class)
public interface UserMapper{
    @Insert("insert into t_user(id,name,phone) values(null,#{name},#{phone})")
    int addUser(User user);
    @Select("select * from t_user")
    List<User> findAllUsers();
    @Select("select * from t_user where id = #{id}")
    User selectUserById(Integer id);

    @Select("select * from t_user")
    List<User> findAll();
}
