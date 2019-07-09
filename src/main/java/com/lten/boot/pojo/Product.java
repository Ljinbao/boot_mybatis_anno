package com.lten.boot.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/7/5 10:16
 */
@Table(name = "t_product")
@Data
@Accessors(chain = true)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 库存
     */
    private int stock;

    /**
     * 价格
     */
    private double price;

    /**
     * 版本号
     */
    private int version;

    /**
     * 备注
     */
    private String note;
}
