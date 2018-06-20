package com.crp.qa.qaCore;

import org.junit.Test;
import java.util.*;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.domain.QaTree;
import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaCore.service.inte.QaPageService;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaTreeException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaCoreApplicationTests {
	
	@Resource(name="qaTreeService")
	QaTreeService qaTreeService;
	
	

	@Test
	public void contextLoads() {
		
	}
	
	
	@Test
	public void test() {
		try {
			QaTreeDto dto = qaTreeService.findByTitle("123123");
		} catch (QaTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
