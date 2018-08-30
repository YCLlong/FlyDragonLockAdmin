package cn.ycl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SystemInterceptor implements HandlerInterceptor {
	
	//返回视图之后开始执行
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}
	
	//controller执行之后但是没有返回视图之前执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	//controller执行前拦截执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		if(request.getRequestURI().contains("getLoginCode")||request.getRequestURI().contains("login")){
			return true;
		}
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("login")!=null && session.getAttribute("login").equals("success")){
			return true;
		}
		response.sendRedirect("login.html");
		return false;
	}

}
