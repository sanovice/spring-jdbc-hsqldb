package test.kr.spring_jdbc_hsqldb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
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
	public void testSimpleJdbc() {
		assertNotNull(app); 
		assertNotNull(dataSource); 
		
		
		SimpleJdbcTemplate simple = new SimpleJdbcTemplate(dataSource); 
		
		String sql = "select * from Product "; 
		
		Map<String, ?> args = new HashMap<String, Object>(); 
		
		
		List results = new ArrayList(); 
		results = simple.queryForList(sql, args);
		
		for(Object result : results) {
			System.out.println("result=" + result); 
		}
		
		
	}

}
