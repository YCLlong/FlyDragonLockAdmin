package cn.ycl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Register;
import cn.ycl.model.RegisterMangerMapper;
import cn.ycl.service.IRegisterMangerService;
import cn.ycl.vo.RegisterMangerQueryVo;

@Service
public class RegisterMangerService implements IRegisterMangerService {
	@Autowired
	private RegisterMangerMapper registerMangerMapper;
	
	//按照条件分页查询注册记录
	@Override
	public PageModel<Register> queryRegister(RegisterMangerQueryVo condition) {
		List<Register> dataList = registerMangerMapper.queryRegister(condition);
		long recourdCount = registerMangerMapper.queryRegisterCount(condition);
		
		PageModel<Register> pageModel = new PageModel<>();
		pageModel.setCurrentPage(condition.getCurrentPage());
		pageModel.setDataList(dataList);
		pageModel.setPageStart(condition.getStartPage());
		pageModel.setRecordNum(recourdCount);
		return pageModel;
	}
}
