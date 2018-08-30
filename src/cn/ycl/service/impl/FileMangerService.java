package cn.ycl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.Send;
import cn.ycl.model.FileMangerMapper;
import cn.ycl.service.IFileMangerService;
import cn.ycl.vo.FileMangerQueryVo;

@Service
public class FileMangerService implements IFileMangerService{
	@Autowired
	private FileMangerMapper fileMangerMapper;
	@Override
	public PageModel<Send> queryFileTransfer(FileMangerQueryVo condition) {
		List<Send> dataList = fileMangerMapper.queryFileTransfer(condition);
		long recourdCount = fileMangerMapper.queryFileCount(condition);
		
		PageModel<Send> pageModel = new PageModel<>();
		pageModel.setCurrentPage(condition.getCurrentPage());
		pageModel.setDataList(dataList);
		pageModel.setPageStart(condition.getStartPage());
		pageModel.setRecordNum(recourdCount);
		return pageModel;
	}

}
