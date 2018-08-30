package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Send;
import cn.ycl.vo.FileMangerQueryVo;

public interface IFileMangerService {
	//按照条件分页查询出注册记录
	public  PageModel<Send> queryFileTransfer(FileMangerQueryVo condition);
}
