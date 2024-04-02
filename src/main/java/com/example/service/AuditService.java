package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.AuditDao;
import com.example.entity.Admin;
import com.example.entity.Audit;
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
public class AuditService {

    @Resource
    private AuditDao auditDao;

    public void add(Audit audit) {
        auditDao.insertSelective(audit);
    }

    public void update(Audit audit) {
        auditDao.updateByPrimaryKeySelective(audit);
    }

    public PageInfo<Audit> findBySearch(Params params) {
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("从token中未解析到用户信息，请重新登录!");
        }
        if ("ROLE_STUDENT".equals(user.getRole())) {
            params.setUserId(user.getId());
        }
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Audit> list = auditDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        auditDao.deleteByPrimaryKey(id);
    }
}
