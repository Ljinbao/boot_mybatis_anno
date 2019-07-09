package com.lten.boot.mapper;

import com.lten.boot.pojo.PurchaseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/7/5 10:44
 */
@Mapper
public interface PurchaseRecordMapper {
    @Insert("insert into t_purchase_record(user_id,product_id,price,quantity,sum,purchase_date,note) values(#{userId},#{productId},#{price},#{quantity},#{sum},now(),#{note})")
    int insertPurchaseRecord(PurchaseRecord record);
}
