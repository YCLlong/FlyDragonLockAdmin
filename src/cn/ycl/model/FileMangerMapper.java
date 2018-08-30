package cn.ycl.model;

import cn.ycl.entity.Send;
import cn.ycl.vo.FileMangerQueryVo;

import java.util.List;

public interface FileMangerMapper {
	List<Send> queryFileTransfer(FileMangerQueryVo condition);
	Long queryFileCount(FileMangerQueryVo condition);
}
