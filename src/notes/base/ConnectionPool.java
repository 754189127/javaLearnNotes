package notes.base;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;

public class ConnectionPool {

	private PoolConfig poolConfig;

	private boolean isActive=false;
	private int connCount;
	//空闲链接
	private Vector<Connection> freeCon=new Vector<Connection>();
	//正在使用的链接
	private Vector<Connection> busyCon=new Vector<Connection>();
	//本地线程变量，用于将连接与当前线程关联起来
	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
	
	public ConnectionPool(PoolConfig poolConfig){
		this.poolConfig = poolConfig;
		//创建连接池时，就应该开始初始化的工作
		init();
	}
	
	private void init(){
		try {
			//Class.forName(poolConfig.getDriverName());
			for (int i = 0; i < poolConfig.getInitConn(); i++) {
				//创建连接对象
				Connection conn = getNewConnection();
				freeCon.add(conn);
				connCount++;
			}
			isActive=true;
			System.out.println("连接池初始化成功");
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
	 * 提供给外部调用获取数据库连接
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
					//添加到正在被占用的集合中
					busyCon.add(conn);
					connCount++;
				}else{
					//当取出的连接不可用时候递归调用找到可用的
					conn = getConnection();
				}
			}else{
				System.out.println("连接池已满，等待中...");
				//已经达到最大连接数情况下不允许新建连接，等待。。。
				wait(poolConfig.getWaitTime());
				conn=getConnection();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("connCount:"+connCount);
		//设置到本地线程
		threadLocal.set(conn);
		return conn;
	}
	
	/**
	 * 当前线程的链接
	 * @return
	 */
	public Connection getCunnrentConnection(){
		return threadLocal.get();
	}
	
	
	/**
	 * 销毁
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
			System.out.println("连接释放成功，唤醒获取连接");
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
