package cn.ycl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ycl.common.PageModel;
import cn.ycl.constant.EUserStatus;
import cn.ycl.entity.TbUserManger;
import cn.ycl.model.UserMangerMapper;
import cn.ycl.service.IUserMangerService;
import cn.ycl.vo.UserMangerQueryVo;
@Service
public class UserMangerService implements IUserMangerService {
	@Autowired
	private UserMangerMapper userMangerMapper;
	
	@Override
	public PageModel<TbUserManger> queryUser(UserMangerQueryVo condition) {
		List<TbUserManger> list = null;
		long recourdCount=0L;
		//所有用户
		if(condition.getUserStatus()== EUserStatus.ALL.getId()) {
			list = userMangerMapper.allUser(condition);
			recourdCount = userMangerMapper.getAllCount();
		}else if (condition.getUserStatus()== EUserStatus.ONLINE.getId()) {
			list = userMangerMapper.onLineUser(condition);
			recourdCount = userMangerMapper.getOnlineCount();
		}else if (condition.getUserStatus()== EUserStatus.OFFLINE.getId()) {
			list = userMangerMapper.offLineUser(condition);
			recourdCount = userMangerMapper.getOffLineCount();
		}
		PageModel<TbUserManger> pageModel = new PageModel<>();
		pageModel.setCurrentPage(condition.getCurrentPage());
		pageModel.setDataList(list);
		pageModel.setPageStart(condition.getStartPage());
		pageModel.setRecordNum(recourdCount);
		return pageModel;
	}

	@Override
	public int addToBlackList(String ip) throws Exception {
		return userMangerMapper.addToBlackList(ip);
	}

	@Override
	public int deleteFromBlackList(String ip) throws Exception {
		return userMangerMapper.deleteFromBlackList(ip);
	}

	@Override
	public int deleteUser(String qq) throws Exception {
		return userMangerMapper.deleteUser(qq);
	}
	
	//判断用户是否时黑名单用户
	@Override
	public boolean isBlackListUser(String ip) throws Exception {
		if(userMangerMapper.isBlackListUser(ip)==1){
			return true;
		}
		return false;
	}
	
	//强制下线
	@Override
	public int byebye(String ip) throws Exception {
		return userMangerMapper.byebye(ip);
	}
}
