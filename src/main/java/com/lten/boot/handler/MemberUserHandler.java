package com.lten.boot.handler;

import org.springframework.stereotype.Component;

/**
 * @description 普通会员
 * @author admin
 * @version 1.0
 * @date 2019/5/22 14:36
 */
@Component
@HandlerType("2")
public class MemberUserHandler extends AbstractHandler {
    @Override
    public String handle(String type) {
        return "普通会员";
    }
}
