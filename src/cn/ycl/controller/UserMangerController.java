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

/*用户管理控制层*/
@Controller
public class UserMangerController {
	@Autowired
	private IUserMangerService userMangerService;

	// 用户管理主页
	@RequestMapping("/userMangerHome")
	public String userMangerHome(@ModelAttribute UserMangerQueryVo condition, Model model) {
		// 调用serverice层拿到数据
		if (condition.getCurrentPage() == null) {
			condition.setCurrentPage(1);
		}
		if(condition.getUserStatus()==null) {
			condition.setUserStatus(EUserStatus.ONLINE.getId());
		}
		// 将数据存入model，返回视图
		PageModel<TbUserManger> pageModel= userMangerService.queryUser(condition);
		model.addAttribute("model", pageModel);
		model.addAttribute("condition", condition);
		// 视图从request域读取数据，呈现
		return "WEB-INF/jsp/userManger.jsp";
	}
	
	//拉黑用户
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
	
	//从黑名单中移除
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
	
	//删除用户
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
	
	//强制下线
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
