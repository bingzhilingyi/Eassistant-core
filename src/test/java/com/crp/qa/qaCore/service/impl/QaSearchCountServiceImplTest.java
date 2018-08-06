package com.crp.qa.qaCore.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.service.inte.QaSearchCountService;
import com.crp.qa.qaCore.util.exception.QaSearchCountException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaSearchCountServiceImplTest {
	
	@Resource(name="qaSearchCountService")
	private QaSearchCountService qaSearchCountService;
	
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void findByDate() throws Exception{	
		Date d = dateFormat1.parse("1000-01-01");
		QaSearchCountDto dto = qaSearchCountService.findByDate(d);
		assertNotNull(dto);
	}
	@Test
	public void findByDate2() throws Exception{	
		Date d = dateFormat1.parse("1123-01-01");
		QaSearchCountDto dto = qaSearchCountService.findByDate(d);
		assertNull(dto);
	}
	@Test
	public void findByDateException() throws Exception{	
		try {
			@SuppressWarnings("unused")
			QaSearchCountDto dto = qaSearchCountService.findByDate(null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入日期为null"));
		}
	}
	
	@Test
	public void findByDateDuring() throws Exception{
		Date from = dateFormat1.parse("1000-01-01");
		Date to = dateFormat1.parse("1000-01-05");
		List<QaSearchCountDto> l = qaSearchCountService.findByDateDuring(from, to);
		assertEquals(5,l.size());
	}
	@Test
	public void findByDateDuring2() throws Exception{
		Date from = dateFormat1.parse("1000-01-06");
		Date to = dateFormat1.parse("1000-01-10");
		List<QaSearchCountDto> l = qaSearchCountService.findByDateDuring(from, to);
		assertEquals(0,l.size());
	}
	@Test
	public void findByDateDuringException() throws Exception{
		try {
			Date to = dateFormat1.parse("1000-01-10");
			qaSearchCountService.findByDateDuring(null, to);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入开始日期为null"));
		}
	}
	@Test
	public void findByDateDuringException2() throws Exception{
		try {
			Date from = dateFormat1.parse("1000-01-10");
			qaSearchCountService.findByDateDuring(from, null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入结束日期为null"));
		}
	}

	@Test
	public void save() throws Exception {
		QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
		qaSearchCountService.save(dto);
		
		Date date = dateFormat1.parse("1999-01-01");
		QaSearchCountDto d = qaSearchCountService.findByDate(date);
		assertNotNull(d);
		assertEquals(new Integer(1234),d.getCountNumber());
		
		qaSearchCountService.delete(d.getCountId());
	}
	@Test
	public void saveException() throws Exception {
		try {
			qaSearchCountService.save(null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入对象为null"));
		}
	}
	@Test
	public void saveException2() throws Exception {
		try {
			QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
			dto.setCountId(9999);
			qaSearchCountService.save(dto);
			fail("a QaSearchCountException expected to be throwed");
		}catch(QaSearchCountException e) {
			assertThat(e.getMessage(),is("传入对象已有主键"));
		}
	}
	@Test
	public void saveException3() throws Exception {
		try {
			QaSearchCountDto dto = new QaSearchCountDto("",1234);
			qaSearchCountService.save(dto);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("日期为空"));
		}
	}
	@Test
	public void saveException4() throws Exception {
		try {
			QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",null);
			qaSearchCountService.save(dto);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("计数为空"));
		}
	}
	@Test
	public void saveException5() throws Exception {
		try {
			QaSearchCountDto dto = new QaSearchCountDto("1000-01-01",123);
			qaSearchCountService.save(dto);
			fail("a QaSearchCountException expected to be throwed");
		}catch(QaSearchCountException e) {
			assertThat(e.getMessage(),is("日期已存在"));
		}
	}
	
	@Test
	public void update() throws Exception{
		QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
		dto = qaSearchCountService.save(dto);
		dto.setCountNumber(1111);
		dto = qaSearchCountService.update(dto);
		assertEquals(new Integer(1111),dto.getCountNumber());
		qaSearchCountService.delete(dto.getCountId());
	}
	@Test
	public void updateException() throws Exception{
		try {
			qaSearchCountService.update(null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("传入对象对null"));
		}
	}
	@Test
	public void updateException0() throws Exception{
		try {
			QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
			dto = qaSearchCountService.update(dto);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("主键为空"));
		}
	}
	@Test
	public void updateException2() throws Exception{
		try {
			QaSearchCountDto dto = new QaSearchCountDto("",1234);
			dto.setCountId(1);
			dto = qaSearchCountService.update(dto);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("日期为空"));
		}
	}
	@Test
	public void updateException3() throws Exception{
		try {
			QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",null);
			dto.setCountId(1);
			dto = qaSearchCountService.update(dto);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("计数为空"));
		}
	}
	@Test
	public void updateException4() throws Exception{
		QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
		Integer id = 0;
		try {
			dto = qaSearchCountService.save(dto);
			id = dto.getCountId();
			dto.setCountId(-11);
			dto = qaSearchCountService.update(dto);
			fail("a QaSearchCountException expected to be throwed");
		}catch(QaSearchCountException e) {
			assertThat(e.getMessage(),is("传入对象主键在数据库中不存在"));
		}finally {
			qaSearchCountService.delete(id);
		}
	}
	@Test
	public void updateException5() throws Exception{
		QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
		Integer id = 0;
		try {
			dto = qaSearchCountService.save(dto);
			id = dto.getCountId();
			dto.setCountDate("1000-01-01");
			dto = qaSearchCountService.update(dto);
			fail("a QaSearchCountException expected to be throwed");
		}catch(QaSearchCountException e) {
			assertThat(e.getMessage(),is("要更新的日期已存在"));
		}finally {
			qaSearchCountService.delete(id);
		}
	}
	
	@Test
	public void delete() throws Exception{
		QaSearchCountDto dto = new QaSearchCountDto("1999-01-01",1234);
		dto = qaSearchCountService.save(dto);
		qaSearchCountService.delete(dto.getCountId());
		
		Date date = dateFormat1.parse("1999-01-01");
		QaSearchCountDto d = qaSearchCountService.findByDate(date);
		assertNull(d);
	}
	@Test
	public void deleteException() throws Exception{
		try {
			qaSearchCountService.delete(null);
			fail("a NullPointerException expected to be throwed");
		}catch(NullPointerException e) {
			assertThat(e.getMessage(),is("id为空"));
		}
	}

}
