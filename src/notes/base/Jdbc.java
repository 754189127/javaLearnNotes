package notes.base;

import com.mysql.jdbc.Connection;

public class Jdbc {
	public static void main(String[] args) {
		// �������ݿ��������������ӣ�����ִ��sql���Ͽ�����
		//Class.forName(className);
		/*try {
			DbUtil dbUtil = new DbUtil();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			if(null==conn){
				System.out.println("����ʧ��");
			}else{
				System.out.println("���ӳɹ�");
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ThreadConn a = new ThreadConn();
		ThreadConn b = new ThreadConn();
		ThreadConn c = new ThreadConn();
		ThreadConn d = new ThreadConn();
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		Thread t4 = new Thread(d);
		t1.setName("�߳�1");
		t2.setName("�߳�2");
		t3.setName("�߳�3");
		t4.setName("�߳�4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}


class ThreadConn implements Runnable {
	public void run() {
		for (int i = 0; i < 20; i++) {
			Connection conn=DbUtil.getConnection();
			System.out.println(Thread.currentThread().getName()+":"+conn);
			try {
				Thread.sleep(1500);
				DbUtil.closeConnection(conn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
