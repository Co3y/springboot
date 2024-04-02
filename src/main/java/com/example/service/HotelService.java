package com.example.service;

import com.example.dao.HotelDao;
import com.example.entity.Hotel;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:41
 */

@Service
public class HotelService {

    @Resource
    private HotelDao hotelDao;

    public PageInfo<Hotel> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Hotel> list = hotelDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Hotel hotel) {
        hotelDao.insertSelective(hotel);
    }

    public void update(Hotel hotel) {
        hotelDao.updateByPrimaryKeySelective(hotel);
    }

    public void delete(Integer id) {
        hotelDao.deleteByPrimaryKey(id);
    }
}
