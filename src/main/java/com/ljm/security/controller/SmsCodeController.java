package com.ljm.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/sms-code")
public class SmsCodeController {

    @GetMapping("/send")
    public void sendSmsCode(HttpServletRequest request,
                            @RequestParam String phoneNumber) {

        String code = RandomStringUtils.randomAlphabetic(6);
        log.info("给手机号{}发送验证码：{}", phoneNumber, code);
        request.getSession().setAttribute("SMS_CODE", code);
    }

}
