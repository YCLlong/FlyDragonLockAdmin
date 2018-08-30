package cn.ycl.model;

import java.util.List;

import cn.ycl.entity.TbUserManger;
import cn.ycl.vo.UserMangerQueryVo;

//�û�����mapper
public interface UserMangerMapper {
	//��ȡ�����û�
	public List<TbUserManger> onLineUser(UserMangerQueryVo vo);
	
	//��ȡ�����û�
	public List<TbUserManger> offLineUser(UserMangerQueryVo vo);
	
	//��ȡ�����û�
	public List<TbUserManger> allUser(UserMangerQueryVo vo);
	
	//��ȡ��Ӧ������
	public int getOnlineCount();
	
	public int getOffLineCount();
	
	public int getAllCount();
	
	//���û���ӵ�������
	public int addToBlackList(String ip) throws Exception;
	
	//���û��Ӻ��������Ƴ�
	public int deleteFromBlackList(String ip) throws Exception;
	
	//ɾ���û�
	public int deleteUser(String qq) throws Exception;
	
	//�ж��û��Ƿ��Ǻ������û�
	public int isBlackListUser(String ip) throws Exception;
	
	//ǿ������
	public int byebye(String ip) throws Exception;
}
