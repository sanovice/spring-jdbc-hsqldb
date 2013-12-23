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
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;


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
		
		
		List<?> results = new ArrayList<Object>(); 
		results = simple.queryForList(sql, args);
		
		for(Object result : results) {
			System.out.println("type=" + result.getClass() + " value=" + result ); 
		}
		
		// dest insert 
		//CREATE TABLE Product2(ID INTEGER PRIMARY KEY,Name VARCHAR(30),Price DECIMAL);
		String sql_insert = "INSERT INTO Product2 VALUES(:ID, :NAME, :PRICE)";
		
		for(Object result : results) {
			simple.update(sql_insert, (Map<String, ?>)result); 
		}
		
		// dest result 
		List<?> rs2 = new ArrayList<Object>();
		rs2 = simple.queryForList("select * from Product2", args);
		System.out.println(rs2);
		
		
		System.out.println("count=" + SimpleJdbcTestUtils.countRowsInTable(simple, "Product2"));
		
		
		//students select 
		List<?> rs3 = new ArrayList<Object>();
		rs3 = simple.queryForList("select * from students", args);
		System.out.println(rs3);
	}

}
