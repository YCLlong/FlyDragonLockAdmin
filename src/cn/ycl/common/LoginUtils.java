package cn.ycl.common;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class LoginUtils {
	public static String getRandCode() {
		synchronized (LoginUtils.class) {
			Random random = new Random();
			String code = "";
			for (int i = 0; i < 8; i++) {
				code = code + random.nextInt(10) + "";
			}
			return code;
		}
	}

	//��ȡ�û���ʵ��IP��ַ
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
