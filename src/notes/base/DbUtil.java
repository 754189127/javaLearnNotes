package notes.base;

import java.io.IOException;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.mysql.jdbc.Connection;

import ch.qos.logback.core.db.dialect.DBUtil;

/**
 * 数据库连接工具
 * @author wguo
 * @date 2017年7月25日 下午5:02:37
 */
public class DbUtil {
	private static PoolConfig config=new PoolConfig();
	static{
		Properties prop = new Properties();
		try {
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("notes/db.properties"));
			config.setUrl(prop.getProperty("jdbc.url"));
			config.setUserName(prop.getProperty("jdbc.name"));
			config.setPassword(prop.getProperty("jdbc.password"));
			config.setCheckPeriod(3000);
			//config.setCheckPeriod(Long.valueOf(eval(prop.getProperty("jdbc.checkPeriod",String.valueOf(1000*60))).toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	private static ConnectionPool connPool = new ConnectionPool(config);
	public static Connection getConnection(){
		return connPool.getConnection();
	}
	public static void closeConnection(Connection conn){
		connPool.releaseConnection(conn);
	}
	
	public static Object eval(String operator){
		   ScriptEngineManager manager = new ScriptEngineManager();
	       ScriptEngine engine = manager.getEngineByName("js");
	       Object result=null;
	       try{
	    	   result = engine.eval(operator);
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
	       System.out.println("result:"+result.toString());
	       return result;
	}
	
	
}
