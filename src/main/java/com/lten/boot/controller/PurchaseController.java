package com.lten.boot.controller;

import com.lten.boot.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/7/5 11:22
 */
@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService = null;

    @RequestMapping("/test")
    public ModelAndView testPage() {
        ModelAndView mav = new ModelAndView("test");
        return mav;
    }

    @RequestMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity) {
        System.out.println(userId + " ------" + productId + "---------------" + quantity);
        boolean success = purchaseService.purchase(userId,productId,quantity);
        String message = success ? "抢购成功" : "抢购失败";
        Result result = new Result(success,message);
        return  result;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Result {
        private boolean success = false;
        private String message = null;
    }
}
