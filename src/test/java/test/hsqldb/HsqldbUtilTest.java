package test.hsqldb;

import static org.junit.Assert.*;

import org.hsqldb.Server;
import org.junit.Test;

public class HsqldbUtilTest {

	@Test
	public void test() {

		Server hsql = HsqldbUtil.openHsqldbServer();
		
		assertNotNull("OK", hsql);
		
	}

}
