package com.example.service;

import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.exception.CustomException;
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
public class AdminService {

    @Resource
    private AdminDao adminDao;

    public List<Admin> getAll() {
        return adminDao.selectAll();
    }

    public PageInfo<Admin> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Admin> list = adminDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        // 非空判断
        if (admin.getName() == null || "".equals(admin.getName())) {
            throw new CustomException("用户名不能为空");
        }
        // 管理员姓名判重
        Admin user = adminDao.findByName(admin.getName());
        if (user != null) {
            throw new CustomException("该用户名已存在，请更换用户名");
        }
        // 初始化密码
        if (admin.getPassword() == null) {
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }

    public Admin login(Admin admin) {
        // 非空判断
        if (admin.getName() == null || "".equals(admin.getName())) {
            throw new CustomException("用户名不能为空");
        }
        if (admin.getPassword() == null || "".equals(admin.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        Admin user = adminDao.findByNameAndPassword(admin.getName(), admin.getPassword());
        if (user == null) {
            throw new CustomException("用户名或密码输入错误");
        }
        // 生成对应的token，返回给前台
        String token = JwtTokenUtils.genToken(user.getId().toString(), user.getPassword());
        user.setToken(token);
        return user;
    }

    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
