package cn.ycl.model;

import java.util.List;

import cn.ycl.entity.Register;
import cn.ycl.vo.RegisterMangerQueryVo;

public interface RegisterMangerMapper {
	List<Register> queryRegister(RegisterMangerQueryVo condition);
	
	long queryRegisterCount(RegisterMangerQueryVo condition);
}
