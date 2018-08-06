package com.crp.qa.qaCore;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.service.inte.QaSearchCountService;
import com.crp.qa.qaCore.util.counter.QaCountSaver;
import com.crp.qa.qaCore.util.counter.QaCounter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaCounterTest {
	
	@Autowired
	QaSearchCountService qaSearchCountService;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void count() {
		QaCounter counter = QaCounter.getInstance();
		Date d = null;
		try {
			d = formatter.parse("1000-01-01");
			Integer num = counter.getCount(d);
			assertNull(num);
			int i = 10;
			while(i>0) {
				counter.count(d);
				i--;
			}
			num = counter.getCount(d);
			assertEquals(new Integer(10),num);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			counter.delCount(d);
		}
	}

	@Test
	public void saveOrUpdate() throws Exception{
		QaCounter c = QaCounter.getInstance();
		c.saveOrUpdate(formatter.parse("9999-01-01"), 22);
		QaSearchCountDto d = qaSearchCountService.findByDate(formatter.parse("9999-01-01"));
		assertNotNull(d);
		assertEquals(d.getCountNumber(),new Integer(22));
		qaSearchCountService.delete(d.getCountId());
	}
}
