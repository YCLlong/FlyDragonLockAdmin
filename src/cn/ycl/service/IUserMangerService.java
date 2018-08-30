package cn.ycl.service;

import cn.ycl.common.PageModel;
import cn.ycl.entity.TbUserManger;
import cn.ycl.vo.UserMangerQueryVo;

public interface IUserMangerService {
	public  PageModel<TbUserManger> queryUser(UserMangerQueryVo vo);
	
	//���û���ӵ�������
	public int addToBlackList(String ip) throws Exception;
	
	//���û��Ӻ��������Ƴ�
	public int deleteFromBlackList(String ip) throws Exception;
	
	//ɾ���û�
	public int deleteUser(String qq) throws Exception;
	
	//�ж��û�����ip��ַ�Ƿ��Ǻ������е��û�
	public boolean isBlackListUser(String ip) throws Exception;
	
	//ǿ������
	public int byebye(String ip) throws Exception;
}
