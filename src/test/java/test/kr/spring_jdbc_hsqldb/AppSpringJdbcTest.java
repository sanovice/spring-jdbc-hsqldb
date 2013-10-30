package test.kr.spring_jdbc_hsqldb;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration({"/config/app-root-test.xml", "/config/app-datasource-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppSpringJdbcTest {


	@Autowired
	ApplicationContext app; 
	
	@Autowired
	DataSource dataSource;
	
	
	
	@Test
	public void test() {
		assertNotNull(app); 
		assertNotNull(dataSource); 
		
	}

}
