package cn.ycl.net;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

//登陆验证码管理
public class LoginCodeManger implements Runnable {
	private HttpSession session;
	public LoginCodeManger(HttpSession session){
		this.session = session;
	}
	private Timer timer = new Timer();
	@Override
	public void run() {
		//开启session管理现成
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//1分钟没有登陆成功就销毁这个回话
				if(session.getAttribute("login")==null || !session.getAttribute("login").equals("success")){
					session.invalidate();
					System.out.println("管理员没有即时登录，session销毁成功");
				}
				timer.cancel();
				System.out.println("session登录管理线程执行结束");
			}
		}, 1000*60);
	}

}
