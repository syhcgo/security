package com.ljm.security.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更改spring security默认的登录成功处理逻辑
 */
@Slf4j
public class JSONAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

		log.info("登录成功，用户信息为：{}", authentication.toString());
		JSONObject returnJSON = new JSONObject();
		returnJSON.put("status", "success");
		printJSON(response, returnJSON);
	}

	private void printJSON(HttpServletResponse response, JSONObject returnData) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(returnData.toString());
	}
}
