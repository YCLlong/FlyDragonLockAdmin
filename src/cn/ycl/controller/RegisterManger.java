package cn.ycl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Register;
import cn.ycl.service.impl.RegisterMangerService;
import cn.ycl.vo.RegisterMangerQueryVo;

@Controller
public class RegisterManger {
	@Autowired
	private RegisterMangerService registerMangerService;
	//×¢²á¼ÇÂ¼Ê×Ò³
	@RequestMapping("/registerHome")
	public String registerHome(@ModelAttribute RegisterMangerQueryVo condition,Model model){
		if(condition==null){
			condition = new RegisterMangerQueryVo();
			condition.setCurrentPage(1);
		}
		if(condition.getCurrentPage()==null || condition.getCurrentPage()<=0){
			condition.setCurrentPage(1);
		}
		PageModel<Register> pageModel = registerMangerService.queryRegister(condition);
		model.addAttribute("model", pageModel);
		model.addAttribute("condition", condition);
		return "WEB-INF/jsp/registerManger.jsp";
	}
}
