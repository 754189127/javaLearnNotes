package notes.base;

import com.mysql.jdbc.Connection;

public class Jdbc {
	public static void main(String[] args) {
		// 加载数据库驱动，建立连接，访问执行sql，断开连接
		//Class.forName(className);
		/*try {
			DbUtil dbUtil = new DbUtil();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			if(null==conn){
				System.out.println("连接失败");
			}else{
				System.out.println("连接成功");
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ThreadConn a = new ThreadConn();
		ThreadConn b = new ThreadConn();
		ThreadConn c = new ThreadConn();
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		t1.setName("线程1");
		t2.setName("线程2");
		t3.setName("线程3");
		t1.start();
		t2.start();
		t3.start();
	}
}


class ThreadConn implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			Connection conn=DbUtil.getConnection();
			System.out.println(Thread.currentThread().getName()+":"+conn);
			try {
				Thread.sleep(3000);
				DbUtil.closeConnection(conn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
