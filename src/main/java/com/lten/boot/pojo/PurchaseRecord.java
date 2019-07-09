package com.lten.boot.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/7/5 10:23
 */
@Data
@Accessors(chain = true)
@Table(name = "t_purchase_record")
public class PurchaseRecord {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 产品编号
     */
    private int productId;
    /**
     * 价格
     */
    private double price;
    /**
     * 数量
     */
    private int quantity;

    /**
     * 总价
     */
    private double sum;

    /**
     * 购买日期
     */
    private Date purchaseDate;

    /**
     * 备注
     */
    private String note;
}
