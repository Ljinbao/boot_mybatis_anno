package com.lten.boot.mapper;

import com.lten.boot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lijinbao
 * @data   2019/3/7
 */
@Mapper
public interface UserMapper{
    @Insert("insert into t_user(id,name,phone) values(null,#{name},#{phone})")
    int addUser(User user);
    @Select("select * from t_user")
    List<User> findAllUsers();
}
