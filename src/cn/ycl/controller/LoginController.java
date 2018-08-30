package cn.ycl.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ycl.common.LoginUtils;
import cn.ycl.net.LoginCodeManger;
import cn.ycl.net.Mail;

//用户登录
@Controller
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/getLoginCode")
	public void getLoginCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//创建session
		HttpSession session = request.getSession(true);
		if(session.getAttribute("code")!=null && !session.getAttribute("code").equals("")){
			//通知客户端不要频繁操作
			response.getWriter().append("请勿非法获取验证码");
			log.info(LoginUtils.getIpAddress(request) + "非法获取登陆验证码");
		}else{
			
			//创建随机码
			String codeString = LoginUtils.getRandCode();
			//通过邮件发送
			/*try {
				Mail.sendMail(Mail.ADMIN_MAIL, "帅气可爱的燕成龙管理员，于"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
						"在ip地址为：" + LoginUtils.getIpAddress(request) + "请求登录验证码，您本次登录飞龙锁系统的验证码为" + codeString + ",1分钟内有效哦~");
			} catch (Exception e) {
				response.getWriter().append("获取验证码失败，请稍后重试");
			}*/
			//存到session
			session.setAttribute("code", codeString);
			//最大时间数为1分钟,启动session回话管理
			new Thread(new LoginCodeManger(session)).start();
			log.info("验证码【"+codeString+"】发送成功，操作ip" + LoginUtils.getIpAddress(request));
			response.getWriter().append("success");
		}
	}

	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,String userName,String userCode) throws IOException{
		//登录，从session获取验证码，如果存在，并且和用户传递过来的验证码一致就可以登录
		HttpSession session = request.getSession(false);
		if(session==null){
			//登陆失败
			log.info("登陆失败，没有获取验证码，操作ip:" + LoginUtils.getIpAddress(request));
			return "redirect:login.html";
		}
		//说明已经发送过验证码
		if(session.getAttribute("login")!=null && session.getAttribute("login").equals("success")){
			return "redirect:userMangerHome.action";
		}
		if(userName.equals("251121753") && userCode!=null){
			String code = (String) request.getSession(false).getAttribute("code");
			if(userCode.equals(code)){
				//登录成功，跳转到主页
				session.setAttribute("login", "success");
				session.setAttribute("ip", LoginUtils.getIpAddress(request));
				//当5分钟不操作时就消除这个session
				session.setMaxInactiveInterval(60*5);
				log.info("登陆成功，操作ip" + LoginUtils.getIpAddress(request));
				//登录成功之后返回在线用户
				return "redirect:userMangerHome.action";
			}else{
				//登录失败，通过邮箱通知管理员
				log.info("登陆失败，验证码不正确。操作ip：" + LoginUtils.getIpAddress(request));
				return "redirect:login.html";
			}
		}else{
			//登录失败，跳转到登陆界面
			log.info("登陆失败，用户名或者验证码没有填写，操作ip：" + LoginUtils.getIpAddress(request));
			return "redirect:login.html";
		}
	}
}
