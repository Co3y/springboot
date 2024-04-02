package com.example.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Book;
import com.example.entity.Params;
import com.example.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:49
 */

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Book> info = bookService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("修改图书信息")
    public Result save(@RequestBody Book book) {
        if (book.getId() == null) {
            bookService.add(book);
        } else {
            bookService.update(book);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @AutoLog("删除图书信息")
    public Result delete(@PathVariable Integer id) {
        bookService.delete(id);
        return Result.success();
    }

    /*
    首页饼图
     */
    @GetMapping("/echarts/bie")
    public Result bie() {
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", key);
                map.put("value", collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    /*
    首页柱状图
     */
    @GetMapping("/echarts/bar")
    public Result bar() {
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));

        List<String> xAxis = new ArrayList<>();
        List<Long> yAxis = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                xAxis.add(key);
                yAxis.add(collect.get(key));
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("xAxis", xAxis);
        map.put("yAxis", yAxis);

        return Result.success(map);
    }
}
