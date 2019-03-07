package com.lten.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author lijinbao
 * @data   2019/3/7
 */
@Mapper
public interface OrderMapper {
    @Update("update t_order set status = 6 where status = 1 and createTime < #{date}")
    int updateStatus(Date date);
}
