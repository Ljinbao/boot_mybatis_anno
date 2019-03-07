package com.lten.boot;

import com.lten.boot.pojo.User;
import com.lten.boot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootMybatisAnnoApplicationTests {

    @Autowired
    private UserService userService;

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
        List<User> allUser = userService.findAllUser(2, 5);
        allUser.forEach(x -> System.out.println(x.toString()));
    }

}
