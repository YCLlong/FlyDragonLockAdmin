package cn.ycl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ycl.common.PageModel;
import cn.ycl.constant.EUserStatus;
import cn.ycl.entity.TbUserManger;
import cn.ycl.service.IUserMangerService;
import cn.ycl.vo.UserMangerQueryVo;

/*�û�������Ʋ�*/
@Controller
public class UserMangerController {
	@Autowired
	private IUserMangerService userMangerService;

	// �û�������ҳ
	@RequestMapping("/userMangerHome")
	public String userMangerHome(@ModelAttribute UserMangerQueryVo condition, Model model) {
		// ����serverice���õ�����
		if (condition.getCurrentPage() == null) {
			condition.setCurrentPage(1);
		}
		if(condition.getUserStatus()==null) {
			condition.setUserStatus(EUserStatus.ONLINE.getId());
		}
		// �����ݴ���model��������ͼ
		PageModel<TbUserManger> pageModel= userMangerService.queryUser(condition);
		model.addAttribute("model", pageModel);
		model.addAttribute("condition", condition);
		// ��ͼ��request���ȡ���ݣ�����
		return "WEB-INF/jsp/userManger.jsp";
	}
	
	//�����û�
	@RequestMapping("/addToBlackList")
	public void addToBlackList(String ip,HttpServletResponse response) throws IOException {
		if(ip!=null && !ip.equals("")) {
			try {
				if(userMangerService.addToBlackList(ip)==1) {
					response.getWriter().write("success");
				}else {
					response.getWriter().write("fail");
				}
			} catch (Exception e) {
				response.getWriter().write("fail");
			}
		}
	}
	
	//�Ӻ��������Ƴ�
	@RequestMapping("/deleteFromBlackList")
	public void deleteFromBlackList(String ip,HttpServletResponse response) throws IOException {
		if(ip!=null && !ip.equals("")) {
			try {
				if(userMangerService.deleteFromBlackList(ip)==1) {
					response.getWriter().write("success");
				}else {
					response.getWriter().write("fail");
				}
			} catch (Exception e) {
				response.getWriter().write("fail");
			}
		}
	}
	
	//ɾ���û�
	@RequestMapping("/deleteUser")
	public void deleteUser(String qq,HttpServletResponse response) throws IOException {
		if(qq!=null && !qq.equals("")) {
			try {
				if(userMangerService.deleteUser(qq)==1) {
					response.getWriter().write("success");
				}else {
					response.getWriter().write("fail");
				}
			} catch (Exception e) {
				response.getWriter().write("fail");
			}
		}
	}
	
	//ǿ������
	@RequestMapping("byebye")
	public void byebye(String ip,HttpServletResponse response) throws IOException{
		if(ip!=null && !ip.equals("")) {
			try {
				if(userMangerService.byebye(ip)==1) {
					response.getWriter().write("success");
				}else {
					response.getWriter().write("fail");
				}
			} catch (Exception e) {
				response.getWriter().write("fail");
			}
		}
	}
	
}
