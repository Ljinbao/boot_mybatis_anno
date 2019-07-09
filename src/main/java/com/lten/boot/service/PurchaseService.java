package com.lten.boot.service;

/**
 * @author Thoms
 * @date 2019/7/5 10:54
 */
public interface PurchaseService {
    public boolean purchase(Long userId,Long productId,int quantity);
}
