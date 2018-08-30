package cn.ycl.model;

import java.util.List;

import cn.ycl.entity.TbUserManger;
import cn.ycl.vo.UserMangerQueryVo;

//用户管理mapper
public interface UserMangerMapper {
	//获取在线用户
	public List<TbUserManger> onLineUser(UserMangerQueryVo vo);
	
	//获取离线用户
	public List<TbUserManger> offLineUser(UserMangerQueryVo vo);
	
	//获取所有用户
	public List<TbUserManger> allUser(UserMangerQueryVo vo);
	
	//获取对应的数量
	public int getOnlineCount();
	
	public int getOffLineCount();
	
	public int getAllCount();
	
	//将用户添加到黑名单
	public int addToBlackList(String ip) throws Exception;
	
	//将用户从黑名单中移除
	public int deleteFromBlackList(String ip) throws Exception;
	
	//删除用户
	public int deleteUser(String qq) throws Exception;
	
	//判断用户是否是黑名单用户
	public int isBlackListUser(String ip) throws Exception;
	
	//强制下线
	public int byebye(String ip) throws Exception;
}
