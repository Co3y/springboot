package com.example.controller;

import com.example.common.CaptchaConfig;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ZengFK
 * @Date: 2024/3/26 19:18
 */
@CrossOrigin
@RestController
@RequestMapping
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // png类型
//        SpecCaptcha captcha = new SpecCaptcha(135, 33, 3);
//        captcha.setCharType(Captcha.TYPE_ONLY_UPPER);
//        // 首先把验证码在后台保存一份，但是不能保存在session，可以存在redis，也可以存在后台的Map里
//        CaptchaConfig.CAPTCHA_MAP.put(key, captcha.text().toLowerCase());
//        CaptchaUtil.out(captcha, request, response);

        // 算数类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(135, 33);
        // 默认两位
        captcha.setLen(3);
        // 获取运算的公式: 3+2=?
        captcha.getArithmeticString();
        captcha.text();
        CaptchaConfig.CAPTCHA_MAP.put(key, captcha.text().toLowerCase());
        CaptchaUtil.out(captcha, request, response);

    }
}
