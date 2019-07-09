package com.lten.boot.service;

import com.lten.boot.mapper.ProductMapper;
import com.lten.boot.mapper.PurchaseRecordMapper;
import com.lten.boot.pojo.Product;
import com.lten.boot.pojo.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thoms
 * @version 1.0
 * @date 2019/7/5 10:55
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper = null;

    @Autowired
    private PurchaseRecordMapper recordMapper = null;

    /**
     * 处理购买业务
     * @param userId    用户ID
     * @param productId 产品ID
     * @param quantity  产品数量
     * @return 购买是否成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean purchase(Long userId, Long productId, int quantity) {
        //get Product
        Product product = productMapper.getProduct(productId);
        //compare quantity and stock
        if (product.getStock() < quantity) {
            //stock not enough
            return false;
        }
        //decrease stock
        productMapper.decreaseProduct(productId,quantity);
        //init product_record
        PurchaseRecord purchaseRecord = this.initPurchaseRecord(userId,product,quantity);
        //insert record
        recordMapper.insertPurchaseRecord(purchaseRecord);
        return true;
    }

    private PurchaseRecord initPurchaseRecord(Long userId, Product product, int quantity) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setNote("购买日志：时间：" + System.currentTimeMillis());
        purchaseRecord.setPrice(product.getPrice());
        purchaseRecord.setProductId(product.getId());
        purchaseRecord.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        purchaseRecord.setSum(sum);
        purchaseRecord.setUserId(userId);
        return purchaseRecord;
    }
}
