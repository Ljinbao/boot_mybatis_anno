package com.lten.boot.security;

import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/7/1 11:26
 */
//public class CustomSecurity extends WebSecurityConfigurerAdapter {

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编辑器（必须添加，不然会抛异常）
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin")
                .password(passwordEncoder.encode("123123")).roles("USER", "ADMIN")
                .and().withUser("myUser").password(passwordEncoder.encode("456456")).roles("USER");

    }
*/
    //配置请求路径访问权限
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //只需要通过验证就可以访问所有的请求
//        http
//               //限定只对用户签名成功的请求
//               .authorizeRequests()
//               //限定所有请求
//               .anyRequest()
//               //对所有签名成功的用户允许方法
//               .authenticated()
//               //and()表示连接词，formLogin代表使用Spring Security默认的登录页面
//                .and().formLogin()
//                //httpBasic说明启用http基础认证
//               .and().httpBasic();

       //自定义配置请求路径访问权限 使用ANT风格限定请求
        http.authorizeRequests()
                //限定/user/welcome请求并赋予角色USER或者ADMIN
                .antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
                //限定/admin/**路径下所有请求并赋予角色ROLE_ADMIN
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //其他请求允许签名后访问
                .anyRequest().permitAll()
                //对于没有配置权限的其他请求允许匿名访问
                .and().anonymous()
                .and().formLogin().and().httpBasic();
    }*/

    //使用SpringEL配置访问权限

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //使用SpringEL限定User和admin
                //限定/user/**路径下的请求并赋予user或者admin角色
                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
                ///admin/welcome1路径下设置角色ROLE_ADMIN并要求完成登录认证
                .antMatchers("/admin/welcome1").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
                //限定/admin/welcome2访问权限给角色ROLE_ADMIN，允许不完整登录
                .antMatchers("/admin/welcome2").access("hasAuthority('ROLE_ADMIN')").and().rememberMe().and().formLogin().and().httpBasic();
    }
*/

//}
