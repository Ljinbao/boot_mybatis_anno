package com.lten.boot.handler;

import org.springframework.stereotype.Component;

/**
 * @description 超级会员
 * @author admin
 * @version 1.0
 * @date 2019/5/22 14:34
 */
@Component
@HandlerType("1")
public class SuperUserHandler extends AbstractHandler {
    @Override
    public String handle(String type) {
        return "超级会员";
    }
}
