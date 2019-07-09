package com.lten.boot.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 9:53
 */
@Table(name = "t_user")
@Data
@Accessors(chain = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

}
