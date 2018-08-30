package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.TbUserManger;
import cn.ycl.vo.UserMangerQueryVo;

public interface IUserMangerService {
	public  PageModel<TbUserManger> queryUser(UserMangerQueryVo vo);
	
	//将用户添加到黑名单
	public int addToBlackList(String ip) throws Exception;
	
	//将用户从黑名单中移除
	public int deleteFromBlackList(String ip) throws Exception;
	
	//删除用户
	public int deleteUser(String qq) throws Exception;
	
	//判断用户所在ip地址是否是黑名单中的用户
	public boolean isBlackListUser(String ip) throws Exception;
	
	//强制下线
	public int byebye(String ip) throws Exception;
}
