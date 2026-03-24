package com.kuaiyukuaikuai;

import com.kuaiyukuaikuai.mapper.EmpMapper;
import com.kuaiyukuaikuai.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TliasWebManagementApplicationTests {

	@Autowired
	private EmpMapper empMapper;

	@Test
	public void testList(){
		List<Emp> empList = empMapper.findAll();
		empList.forEach(System.out::println);
	}
}