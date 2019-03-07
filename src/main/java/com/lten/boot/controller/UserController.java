package com.lten.boot.controller;

import com.lten.boot.pojo.User;
import com.lten.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 9:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/all/{pageNum}/{pageSize}")
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
}
