package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Register;
import cn.ycl.vo.RegisterMangerQueryVo;

//ע���¼����
public interface IRegisterMangerService {
	//����������ҳ��ѯ��ע���¼
	public  PageModel<Register> queryRegister(RegisterMangerQueryVo condition);
}
