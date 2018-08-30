package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Register;
import cn.ycl.vo.RegisterMangerQueryVo;

//注册记录管理
public interface IRegisterMangerService {
	//按照条件分页查询出注册记录
	public  PageModel<Register> queryRegister(RegisterMangerQueryVo condition);
}
