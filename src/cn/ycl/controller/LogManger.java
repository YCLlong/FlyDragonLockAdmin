package cn.ycl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogManger {
	//��־������ҳ
	@RequestMapping("/logHome")
	public String logHome(){
		
		return "WEB-INF/jsp/logManger.jsp";
	}
}
