package test.hsqldb;


import java.io.IOException;
import java.util.Properties;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public abstract class HsqldbUtil {
	
	private static Logger log = LoggerFactory.getLogger("HsqldbUtil");
	
	static Server hsqlServer = null; 
	
	/**
	 * hsqldb 서버가 시작되지 않았으면 서버를 시작함.
	 * @return
	 */
	public static Server openHsqldbServer() {
		
		Server server = null; 
		if( isOpenedServer() == false ) {
			server = new Server();
		}
		Properties pr = getDefaultPropertis() ;
		//app.jdbc.driverClassName=org.hsqldb.jdbcDriver, app.jdbc.username=SA, app.jdbc.url=jdbc:hsqldb:mem:testdb, app.jdbc.password=}
		
		
		Properties hp = new Properties();
		hp.put("jdbc.url", pr.get("app.jdbc.url"));
		hp.put("jdbc.driverClassName", pr.get("app.jdbc.driverClassName"));
		hp.put("jdbc.username", "app.jdbc.username");
		
		HsqlProperties p = new HsqlProperties();
		p.addProperties(hp);
		
		server.setProperties(p);
		
		
		server.start();
		
		log.info("hsql server status = {}", server.getStateDescriptor());
		
		return server; 
		
		
		
	}
	
	private static boolean isOpenedServer() {
		boolean isopened = false; 
		
		if( hsqlServer != null )  {
			//if ( hsqlSever. )
			isopened = true;
			
		}
		
		return isopened;
	}

	/**
	 * HSql server 중지처리.
	 */
	public static void closeHsqldbServer() {
		if (hsqlServer != null ) {
			hsqlServer.stop();
			log.info("HSql Server stoped.. hsqlServer={}", hsqlServer);
		} else {
			log.warn("Hsql Server Not started..");
		}
	}

	private static Properties getDefaultPropertis()  {
		String config_properties = "config/spring-test.properties"; 
		Properties pr = null;
		
		try {
			pr = PropertiesLoaderUtils.loadAllProperties(config_properties);
			log.debug("Load config file {}\n values={}", config_properties, pr);
		} catch (IOException e) {
			log.warn("Load config file fail {}\n}", config_properties);
			e.printStackTrace();
		}
		
		return pr;
	}
}
