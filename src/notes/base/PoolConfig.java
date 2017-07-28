package notes.base;

/***
 * 数据库连接池配置信息
 * @author wguo
 * @date 2017年7月25日 下午4:52:34
 */
public class PoolConfig {
	
	private String driverName;//数据库驱动类
	private String url;//数据库连接地址
	private String userName;
	private String password;
	
	private int minConn = 1;//空闲集合中最小的链接数
	private int maxConn = 5;//空闲集合中最大的链接数
	private int initConn = 5;//初始化空闲集合中链接数
	private int maxActiveCon = 10;//允许产生最大连接数（空闲和使用中的，一般数据库中允许的最大数）
	private int waitTime = 1000;//毫秒，当连接池已达到最大链接数时，并没有空闲连接数，线程需要等待
	private boolean isCheck = false;//是否定时检查连接池的状态，自检机制
	private long checkPeriod = 1000*60;//一分钟检查一次
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMinConn() {
		return minConn;
	}
	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}
	public int getMaxConn() {
		return maxConn;
	}
	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}
	public int getInitConn() {
		return initConn;
	}
	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}
	public int getMaxActiveCon() {
		return maxActiveCon;
	}
	public void setMaxActiveCon(int maxActiveCon) {
		this.maxActiveCon = maxActiveCon;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public long getCheckPeriod() {
		return checkPeriod;
	}
	public void setCheckPeriod(long checkPeriod) {
		this.checkPeriod = checkPeriod;
	}
	
}
