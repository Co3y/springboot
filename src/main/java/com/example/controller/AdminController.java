package com.example.controller;

import com.example.common.AutoLog;
import com.example.common.CaptchaConfig;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ZengFK
 * @Date: 2024/3/20 10:49
 */

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping
    public Result findAll() {
        List<Admin> list = adminService.getAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Admin> info = adminService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Admin admin) {
        if (admin.getId() == null) {
            adminService.add(admin);
        } else {
            adminService.update(admin);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    @AutoLog("用户登录")
    public Result login(@RequestBody Admin admin, @RequestParam String key, HttpServletRequest request) {
        // 判断验证码对不对
        if (!admin.getVerCode().toLowerCase().equals(CaptchaConfig.CAPTCHA_MAP.get(key))){
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }
        Admin loginUser = adminService.login(admin);
        CaptchaConfig.CAPTCHA_MAP.remove(key);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }
}
