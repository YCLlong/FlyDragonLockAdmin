package cn.ycl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Send;
import cn.ycl.service.impl.FileMangerService;
import cn.ycl.vo.FileMangerQueryVo;

@Controller
public class FileMangerController {
	@Autowired
	private FileMangerService fileMangerService;
	@RequestMapping("/fileMangerHome")
	public String fileMangerHome(FileMangerQueryVo condition,Model model){
		if(condition==null){
			condition = new FileMangerQueryVo();
			condition.setCurrentPage(1);
		}
		if(condition.getCurrentPage()==null || condition.getCurrentPage()<=0){
			condition.setCurrentPage(1);
		}
		PageModel<Send> pageModel = fileMangerService.queryFileTransfer(condition);
		model.addAttribute("model", pageModel);
		model.addAttribute("condition", condition);
		return "WEB-INF/jsp/fileTranceManger.jsp";
	}
}
