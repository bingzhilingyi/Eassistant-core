package com.crp.qa.qaCore.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.repository.QaTreeRepository;
import com.crp.qa.qaCore.service.inte.QaSearchHistoryService;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaSearchHistoryImplTest {
	
	@Resource(name="qaSearchHistoryService")
	QaSearchHistoryService qaSearchHistoryService;
	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	@Autowired
    StringRedisTemplate stringRedisTemplate;
	
	//test find by content,exist
	@Test
	public void findByHistoryContent()throws Exception {
		QaSearchHistoryDto d = qaSearchHistoryService.findByHistoryContent("你好");
		assertEquals(new Integer(1),d.getHistoryId());
	}
	
	//test find by content, not exist
	@Test
	public void findByHistoryContent2()throws Exception {
		QaSearchHistoryDto d = qaSearchHistoryService.findByHistoryContent("你好1231231");
		assertNull(d);
	}
	
	//test find by content,null
	@Test
	public void findByHistoryContentException()throws Exception {
		try {
			@SuppressWarnings("unused")
			QaSearchHistoryDto d = qaSearchHistoryService.findByHistoryContent(null);
			fail("expected a QaSearchHistoryException to be throwed");
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("historyContent is null"));
		}

	}
	
	@Test
	public void setHistory()throws Exception {
		//create first
		QaSearchHistoryDto t = qaSearchHistoryService.setHistory("test setHistory");
		QaSearchHistoryDto d = qaSearchHistoryService.findByHistoryContent("test setHistory");
		assertEquals(t.getHistoryId(),d.getHistoryId());
		assertEquals(new Integer(1),d.getRank());
		//rank++
		qaSearchHistoryService.setHistory("test setHistory");
		d = qaSearchHistoryService.findByHistoryContent("test setHistory");
		assertEquals(new Integer(2),d.getRank());
		//delete finally
		qaSearchHistoryService.deleteById(d.getHistoryId());
	}
	
	@Test
	public void setHistoryException()throws Exception {
		try {
			qaSearchHistoryService.setHistory(null);
			fail("expected a QaSearchHistoryException to be throwed");
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("传入title为空！"));
		}
	}
	
	@Test
	public void deleteById()throws Exception {
		QaSearchHistoryDto t = qaSearchHistoryService.setHistory("test setHistory");
		qaSearchHistoryService.deleteById(t.getHistoryId());
		QaSearchHistoryDto d = qaSearchHistoryService.findByHistoryContent("test setHistory");
		assertNull(d);
	}
	
	@Test
	public void deleteByIdException()throws Exception {
		try {
			qaSearchHistoryService.deleteById(null);
			fail("expected a QaSearchHistoryException to be throwed");
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("historyId is null"));
		}
	}
	
	@Test
	public void deleteByIdException2()throws Exception {
		try {
			qaSearchHistoryService.deleteById(-11);
			fail("expected a QaSearchHistoryException to be throwed");
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("historyId not exist"));
		}
	}
	
	//test findTopHistory success
	@Test
	public void findTopHistory()throws Exception {
		List<QaSearchHistoryDto> l = qaSearchHistoryService.findTopHistory(20);
		assertEquals(20,l.size());
		assertTrue(l.get(0).getRank()>l.get(19).getRank());
	}
	
	//test findTopHistory,input null
	@Test
	public void findTopHistory2()throws Exception {
		List<QaSearchHistoryDto> l = qaSearchHistoryService.findTopHistory(null);
		//expected 10 data to be returned
		assertEquals(10,l.size());
	}
	
	//test findTopHistory,input null
	@Test
	public void findTopHistoryException()throws Exception {
		try {
			@SuppressWarnings("unused")
			List<QaSearchHistoryDto> l = qaSearchHistoryService.findTopHistory(0);
			fail("Expected a QaSearchHistoryException to be throwed");
		}catch(QaSearchHistoryException e) {
			assertThat(e.getMessage(),is("输入数字不能小于1!"));
		}
	}
	
	@Test
	public void searchRecord()throws Exception{
		QaTreeDto t = new QaTreeDto("testSearchRecord","SRM","N",1);
		t = qaTreeService.save(t);
		try {
			qaSearchHistoryService.searchRecord("testSearchRecord");
			Thread.sleep(1000);
			t = qaTreeService.findByTitle("testSearchRecord");
			assertEquals(new Integer(1),t.getRank());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			qaTreeService.deleteById(t.getTreeId());
		}
	}
	
	@Test
	public void searchRecord2()throws Exception{
		qaSearchHistoryService.searchRecord(null);
	}
}
