package com.lten.boot.mapper;

import com.lten.boot.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Thoms
 * @date 2019/7/5 10:30
 */
@Mapper
public interface ProductMapper {

    /**
     * 获取产品
     */
    @Select("select id,product_name as productName,stock,price,version,note from t_product where id = #{id} for update")
    Product getProduct(Long id);

    /**
     * 减库存 出现了超发的现象，说明线程之间对共享可变的变量没有
     */
    @Update("update t_product set stock = stock - #{quantity} where id = #{id}")
    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity);
}
