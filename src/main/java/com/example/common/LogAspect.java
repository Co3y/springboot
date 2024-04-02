package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Admin;
import com.example.entity.Log;
import com.example.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZengFK
 * @Date: 2024/3/25 21:48
 * 处理切面的"监控"
 */
@Component
@Aspect
public class LogAspect {

    @Resource
    private LogService logService;

    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable {
        String name = autoLog.value();
        String time = DateUtil.now();
        String userName = "";
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNotEmpty(user)) {
            userName = user.getName();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        Result res = (Result) joinPoint.proceed();

        Object data = res.getData();
        if (data instanceof  Admin) {
            Admin admin = (Admin) data;
            userName = admin.getName();
        }

        Log log = new Log(null, name, time, userName, ip);
        logService.add(log);

        return res;
    }
}
