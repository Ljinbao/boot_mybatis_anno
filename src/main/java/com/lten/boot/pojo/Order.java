package com.lten.boot.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 11:05
 */
@Data
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String  orderId;
    private Long    userId;
    private String  buyerNick;
    private String  address;
    private Date    createTime;
}
