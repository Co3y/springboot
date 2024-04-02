package com.example.controller;


import cn.hutool.core.date.DateUtil;
import com.example.common.Result;
import com.example.dao.HotelDao;
import com.example.entity.Hotel;
import com.example.entity.Reserve;
import com.example.entity.Params;
import com.example.service.HotelService;
import com.example.service.ReserveService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:49
 */

@CrossOrigin
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource
    private ReserveService reserveService;
    @Resource
    private HotelDao hotelDao;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Reserve> info = reserveService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Reserve reserve) {
        Hotel hotel = hotelDao.selectByPrimaryKey(reserve.getHotelId());
        if (hotel.getNum() == 0) {
            return Result.error("酒店已预订完，请预订其他！");
        }
        reserve.setTime(DateUtil.now());
        reserveService.add(reserve);

        hotel.setNum(hotel.getNum() - 1);
        hotelDao.updateByPrimaryKeySelective(hotel);

        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        reserveService.delete(id);
        return Result.success();
    }
}
