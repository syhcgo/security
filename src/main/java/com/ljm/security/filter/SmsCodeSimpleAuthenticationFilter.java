package com.ljm.security.filter;

import com.ljm.security.authentication.SmsCodeSimpleAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SmsCodeSimpleAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeSimpleAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sms-code/login", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, ServletRequestBindingException {

        String phoneNumber = ServletRequestUtils.getStringParameter(request, "phoneNumber");
        String code = ServletRequestUtils.getStringParameter(request, "code");

        //简单的校验一下，大家可以根据自己的逻辑实现这一块
        assert code != null;
        if (!code.equals(request.getSession().getAttribute("SMS_CODE"))) {

            throw new BadCredentialsException("验证码错误");
        }

        log.info("手机号{}通过短信验证码登录成功", phoneNumber);
        //生成一个已认证的Authentication返回，用户名为手机号，密码为空，权限为admin
        return new SmsCodeSimpleAuthenticationToken(phoneNumber, null, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
