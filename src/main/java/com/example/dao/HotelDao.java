package com.example.dao;

import com.example.entity.Hotel;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:41
 */

@Repository
public interface HotelDao extends Mapper<Hotel> {

    List<Hotel> findBySearch(@Param("params") Params params);
}
