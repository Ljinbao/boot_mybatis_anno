package com.lten.boot.handler;

import org.springframework.stereotype.Component;

/**
 * @description 普通用户
 * @author admin
 * @version 1.0
 * @date 2019/5/22 14:37
 */
@Component
@HandlerType("3")
public class NormalUserHandler extends AbstractHandler {
    @Override
    public String handle(String type) {
        return "普通用户";
    }
}

