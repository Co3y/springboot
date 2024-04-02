package com.example.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZengFK
 * @Date: 2024/3/26 19:33
 */
@Component
public class CaptchaConfig {

    public static Map<String, String > CAPTCHA_MAP = new HashMap<>();
}
