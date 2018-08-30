package cn.ycl.net;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

//��½��֤�����
public class LoginCodeManger implements Runnable {
	private HttpSession session;
	public LoginCodeManger(HttpSession session){
		this.session = session;
	}
	private Timer timer = new Timer();
	@Override
	public void run() {
		//����session�����ֳ�
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//1����û�е�½�ɹ�����������ػ�
				if(session.getAttribute("login")==null || !session.getAttribute("login").equals("success")){
					session.invalidate();
					System.out.println("����Աû�м�ʱ��¼��session���ٳɹ�");
				}
				timer.cancel();
				System.out.println("session��¼�����߳�ִ�н���");
			}
		}, 1000*60);
	}

}
