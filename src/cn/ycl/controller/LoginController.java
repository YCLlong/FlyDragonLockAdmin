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

//�û���¼
@Controller
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/getLoginCode")
	public void getLoginCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//����session
		HttpSession session = request.getSession(true);
		if(session.getAttribute("code")!=null && !session.getAttribute("code").equals("")){
			//֪ͨ�ͻ��˲�ҪƵ������
			response.getWriter().append("����Ƿ���ȡ��֤��");
			log.info(LoginUtils.getIpAddress(request) + "�Ƿ���ȡ��½��֤��");
		}else{
			
			//���������
			String codeString = LoginUtils.getRandCode();
			//ͨ���ʼ�����
			/*try {
				Mail.sendMail(Mail.ADMIN_MAIL, "˧���ɰ������������Ա����"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
						"��ip��ַΪ��" + LoginUtils.getIpAddress(request) + "�����¼��֤�룬�����ε�¼������ϵͳ����֤��Ϊ" + codeString + ",1��������ЧŶ~");
			} catch (Exception e) {
				response.getWriter().append("��ȡ��֤��ʧ�ܣ����Ժ�����");
			}*/
			//�浽session
			session.setAttribute("code", codeString);
			//���ʱ����Ϊ1����,����session�ػ�����
			new Thread(new LoginCodeManger(session)).start();
			log.info("��֤�롾"+codeString+"�����ͳɹ�������ip" + LoginUtils.getIpAddress(request));
			response.getWriter().append("success");
		}
	}

	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,String userName,String userCode) throws IOException{
		//��¼����session��ȡ��֤�룬������ڣ����Һ��û����ݹ�������֤��һ�¾Ϳ��Ե�¼
		HttpSession session = request.getSession(false);
		if(session==null){
			//��½ʧ��
			log.info("��½ʧ�ܣ�û�л�ȡ��֤�룬����ip:" + LoginUtils.getIpAddress(request));
			return "redirect:login.html";
		}
		//˵���Ѿ����͹���֤��
		if(session.getAttribute("login")!=null && session.getAttribute("login").equals("success")){
			return "redirect:userMangerHome.action";
		}
		if(userName.equals("251121753") && userCode!=null){
			String code = (String) request.getSession(false).getAttribute("code");
			if(userCode.equals(code)){
				//��¼�ɹ�����ת����ҳ
				session.setAttribute("login", "success");
				session.setAttribute("ip", LoginUtils.getIpAddress(request));
				//��5���Ӳ�����ʱ���������session
				session.setMaxInactiveInterval(60*5);
				log.info("��½�ɹ�������ip" + LoginUtils.getIpAddress(request));
				//��¼�ɹ�֮�󷵻������û�
				return "redirect:userMangerHome.action";
			}else{
				//��¼ʧ�ܣ�ͨ������֪ͨ����Ա
				log.info("��½ʧ�ܣ���֤�벻��ȷ������ip��" + LoginUtils.getIpAddress(request));
				return "redirect:login.html";
			}
		}else{
			//��¼ʧ�ܣ���ת����½����
			log.info("��½ʧ�ܣ��û���������֤��û����д������ip��" + LoginUtils.getIpAddress(request));
			return "redirect:login.html";
		}
	}
}
