package com.crp.qa.qaCore.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaCore.service.inte.QaSearchNoResultService;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaSearchNoResultServiceImplTest {

	@Resource(name="qaSearchNoResultService")
	QaSearchNoResultService qaSearchNoResultService;
	
	@Test
	public void save() throws Exception{
		QaSearchNoResultDto d = new QaSearchNoResultDto();
		d.setRecordTitle("123");
		d = qaSearchNoResultService.save(d);
		assertNotNull(d);
		assertNotNull(d.getRecordId());
	}
	@Test
	public void saveException() throws Exception{
		try {
			qaSearchNoResultService.save(null);
			fail("a Exception expected to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("传入对象为空"));
		}
	}
	@Test
	public void saveException2() throws Exception{
		try {
			QaSearchNoResultDto d = new QaSearchNoResultDto();
			qaSearchNoResultService.save(d);
			fail("a Exception expected to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("标题为空"));
		}
	}
	@Test
	public void saveException3() throws Exception{
		try {
			QaSearchNoResultDto d = new QaSearchNoResultDto();
			d.setRecordId(1);
			d.setRecordTitle("123");
			qaSearchNoResultService.save(d);
			fail("a Exception expected to be throwed");
		}catch(Exception e) {
			assertThat(e.getMessage(),is("传入对象已有id"));
		}
	}
	
	@Test 
	public void findPagedAll()throws Exception{
		QaPagedDto<QaSearchNoResultDto> p = qaSearchNoResultService.findPagedAll(0, 20);
		assertTrue(p.getList().size()>0);
	}
	
	@Test
	public void findByCreationDateBetween() throws Exception{
		QaPagedDto<QaSearchNoResultDto> p = qaSearchNoResultService.findByCreationDateBetween(null, null, 0, 20);
		assertTrue(p.getList().size()>0);
	}
	@Test
	public void findByCreationDateBetween2() throws Exception{
		QaPagedDto<QaSearchNoResultDto> p = qaSearchNoResultService.findByCreationDateBetween(new Date(), null, 0, 20);
		assertTrue(p.getList().size()==0);
	}
}
