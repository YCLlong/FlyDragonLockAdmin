package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Send;
import cn.ycl.vo.FileMangerQueryVo;

public interface IFileMangerService {
	//����������ҳ��ѯ��ע���¼
	public  PageModel<Send> queryFileTransfer(FileMangerQueryVo condition);
}
