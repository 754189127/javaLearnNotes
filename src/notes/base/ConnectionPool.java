package notes.base;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;

public class ConnectionPool {

	private PoolConfig poolConfig;

	private boolean isActive=false;
	private int connCount;
	//��������
	private Vector<Connection> freeCon=new Vector<Connection>();
	//����ʹ�õ�����
	private Vector<Connection> busyCon=new Vector<Connection>();
	//�����̱߳��������ڽ������뵱ǰ�̹߳�������
	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
	
	public ConnectionPool(PoolConfig poolConfig){
		this.poolConfig = poolConfig;
		//�������ӳ�ʱ����Ӧ�ÿ�ʼ��ʼ���Ĺ���
		init();
	}
	
	private void init(){
		try {
			//Class.forName(poolConfig.getDriverName());
			for (int i = 0; i < poolConfig.getInitConn(); i++) {
				//�������Ӷ���
				Connection conn = getNewConnection();
				freeCon.add(conn);
				connCount++;
			}
			isActive=true;
			System.out.println("���ӳس�ʼ���ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized Connection getNewConnection() throws SQLException{
		Connection conn=(Connection) DriverManager.getConnection(poolConfig.getUrl(), poolConfig.getUserName(), poolConfig.getPassword());
		return conn;
	}
	
	/**
	 * �ṩ���ⲿ���û�ȡ���ݿ�����
	 * @return
	 */
	public synchronized Connection getConnection(){
		Connection conn=null;
		try{
			if(connCount<poolConfig.getMaxActiveCon()){
				if(freeCon.size()>0){
					conn=freeCon.get(0);
					freeCon.remove(0);
				}else{
					conn = getNewConnection();
				}
				if(isEnable(conn)){
					//��ӵ����ڱ�ռ�õļ�����
					busyCon.add(conn);
					connCount++;
				}else{
					//��ȡ�������Ӳ�����ʱ��ݹ�����ҵ����õ�
					conn = getConnection();
				}
			}else{
				System.out.println("���ӳ��������ȴ���...");
				//�Ѿ��ﵽ�������������²������½����ӣ��ȴ�������
				wait(poolConfig.getWaitTime());
				conn=getConnection();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("connCount:"+connCount);
		//���õ������߳�
		threadLocal.set(conn);
		return conn;
	}
	
	/**
	 * ��ǰ�̵߳�����
	 * @return
	 */
	public Connection getCunnrentConnection(){
		return threadLocal.get();
	}
	
	
	/**
	 * ����
	 * @param conn
	 */
	public synchronized void releaseConnection(Connection conn){
		if(isEnable(conn)){
			if(freeCon.size()<poolConfig.getMaxActiveCon()){
				freeCon.addElement(conn);
			}else{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			busyCon.remove(conn);
			connCount--;
			threadLocal.remove();
			System.out.println("�����ͷųɹ������ѻ�ȡ����");
			notifyAll();
		}
	}
	
	public synchronized void destory(){
		for (Connection connection : freeCon) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (Connection connection : busyCon) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isActive=false;
		connCount=0;
	}
	
	private boolean isEnable(Connection conn){
		try {
			if(conn==null || conn.isClosed()){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public PoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}
	
	
}
