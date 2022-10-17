package com.snagallery.test.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		String uri = request.getRequestURI();
		
	
		if(userId != null) {
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/mein/view");
				return false;
			}
			
		} else {
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signin/view");
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, ModelAndView ModelAndView) {
		
	}
	

}
