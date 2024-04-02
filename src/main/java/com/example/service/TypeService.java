package com.example.service;


import com.example.dao.TypeDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Type;
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
public class TypeService {

    @Resource
    private TypeDao typeDao;


    public void add(Type type) {
        typeDao.insertSelective(type);
    }

    public void update(Type type) {
        typeDao.updateByPrimaryKeySelective(type);
    }

    public PageInfo<Type> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Type> list = typeDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        typeDao.deleteByPrimaryKey(id);
    }

    public List<Type> findAll() {
        return typeDao.selectAll();
    }
}
