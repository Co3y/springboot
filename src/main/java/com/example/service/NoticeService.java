package com.example.service;


import cn.hutool.core.date.DateUtil;
import com.example.dao.NoticeDao;
import com.example.entity.Admin;
import com.example.entity.Notice;
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
public class NoticeService {

    @Resource
    private NoticeDao noticeDao;

    public void add(Notice notice) {
        notice.setTime(DateUtil.now());
        noticeDao.insertSelective(notice);
    }

    public PageInfo<Notice> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Notice> list = noticeDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void update(Notice notice) {
        notice.setTime(DateUtil.now());
        noticeDao.updateByPrimaryKeySelective(notice);
    }

    public void delete(Integer id) {
        noticeDao.deleteByPrimaryKey(id);
    }

    public List<Notice> findTop() {
        return noticeDao.findTop3();
    }
}
