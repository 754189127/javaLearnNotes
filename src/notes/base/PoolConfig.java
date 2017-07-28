package notes.base;

/***
 * ���ݿ����ӳ�������Ϣ
 * @author wguo
 * @date 2017��7��25�� ����4:52:34
 */
public class PoolConfig {
	
	private String driverName;//���ݿ�������
	private String url;//���ݿ����ӵ�ַ
	private String userName;
	private String password;
	
	private int minConn = 1;//���м�������С��������
	private int maxConn = 5;//���м���������������
	private int initConn = 5;//��ʼ�����м�����������
	private int maxActiveCon = 10;//���������������������к�ʹ���еģ�һ�����ݿ���������������
	private int waitTime = 1000;//���룬�����ӳ��Ѵﵽ���������ʱ����û�п������������߳���Ҫ�ȴ�
	private boolean isCheck = false;//�Ƿ�ʱ������ӳص�״̬���Լ����
	private long checkPeriod = 1000*60;//һ���Ӽ��һ��
	
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
