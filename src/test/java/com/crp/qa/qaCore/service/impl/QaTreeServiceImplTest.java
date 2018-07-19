package com.crp.qa.qaCore.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaTreeException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaTreeServiceImplTest {
	
	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	//测试查找所有根节点正确
	@Test 
	public void findRoot() throws Exception{
		List<QaTreeSimpleDto> list = qaTreeService.findRoot();
		assertTrue(list.size()>0);
	};
	
	//测试查找分页的所有信息正确
	@Test 
	public void findPagedAll() throws Exception{
		QaPagedDto<QaTreeSimpleDto> list = qaTreeService.findPagedAll(0,10);
		assertEquals(10,list.getList().size());
		assertTrue( list.getTotalElements()>0);
		assertTrue( list.getTotalPages()>0);
	}
	
	//测试查找分页的所有信息,传入null时依然正确
	@Test 
	public void findPagedAll2() throws Exception{
		QaPagedDto<QaTreeSimpleDto> list = qaTreeService.findPagedAll(null,null);
		assertEquals(20,list.getList().size()); //默认大小20
		assertTrue( list.getTotalElements()>0);
		assertTrue( list.getTotalPages()>0);
	}
	
	//测试根据父id查找所有子集，有数据
	@Test 
	public void findByParentId() throws Exception{
		List<QaTreeSimpleDto> list = qaTreeService.findByParentId(0);
		assertEquals(4,list.size());
	}
	
	//测试根据父id查找所有子集，无数据
	@Test 
	public void findByParentId2() throws Exception{
		List<QaTreeSimpleDto> list = qaTreeService.findByParentId(-11);
		assertEquals(0,list.size());
	}
	
	//测试根据父id查找所有子集，报错
	@Test
	public void findByParentIdException() throws Exception{
		try {
			@SuppressWarnings("unused")
			List<QaTreeSimpleDto> list = qaTreeService.findByParentId(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入的parentId为空！"));
		}
	}
	
	//测试根据标题精确查询，正常
	@Test 
	public void findByTitle() throws Exception{
		QaTreeDto dto = qaTreeService.findByTitle("SRM");
		assertEquals(new Integer(1),dto.getTreeId());
		assertEquals("SRM",dto.getTitle());
	}
	
	//测试根据标题精确查询,查询不到
	@Test 
	public void findByTitle2() throws Exception{
		QaTreeDto dto = qaTreeService.findByTitle("SRM12312");
		assertNull(dto);
	}
	
	//测试根据标题精确查询,报错
	@Test
	public void findByTitleException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaTreeDto dto = qaTreeService.findByTitle(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入的title为空！"));
		}	
	}
	
	//test find paged data by title like , have response
	@Test
	public void findPagedByTitleLike() throws Exception{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleLike("srm",0,10);
		assertTrue(p.getList().size()>0);
		assertTrue(p.getTotalElements()>0);
		assertTrue(p.getTotalPages()>0);
	}
	
	//test find paged data by title like , no response
	@Test
	public void findPagedByTitleLike2() throws Exception{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleLike("srm1111111",0,10);
		assertTrue(p.getList().size()==0);
		assertTrue(p.getTotalElements()==0);
		assertTrue(p.getTotalPages()==0);
	};
	
	//test find paged data by title like , exception
	@Test
	public void findPagedByTitleLikeException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleLike(null,0,10);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("title is null!"));
		}
	}
	
	//test find by id , exists
	@Test
	public void findById() throws Exception{
		QaTreeDto t = qaTreeService.findById(1);
		assertEquals("SRM", t.getTitle());
	}
	
	//test find by id , not exists
	@Test
	public void findById2() throws Exception{
		QaTreeDto t = qaTreeService.findById(-111);
		assertNull(t);
	}
	
	//test find by id , exception
	@Test
	public void findByIdException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaTreeDto t = qaTreeService.findById(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入的Id为空！"));
		}
	}
	
	//test save success
	@Test
	public void save() throws Exception{
		QaTreeDto t = new QaTreeDto();
		t.setTitle("testSave");
		t.setIsPage("Y");
		QaTreeDto d = qaTreeService.save(t);
		assertTrue(d.getTreeId()!=null);
		//remember delete the data finally
		qaTreeService.deleteById(d.getTreeId());
		d = qaTreeService.findById(d.getTreeId());
		assertNull(d);
	}
	
	//test save null
	@Test
	public void saveException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaTreeDto d = qaTreeService.save(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入对象为空！"));
		}
	}
	
	//test save while dto has id
	@Test
	public void saveException2() throws Exception{
		try {
			QaTreeDto t = new QaTreeDto();
			t.setTreeId(-10);
			t.setTitle("testSave");
			t.setIsPage("Y");
			@SuppressWarnings("unused")
			QaTreeDto d = qaTreeService.save(t);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入对象已有主键！"));
		}
	}
		
	//test save while title allready exist
	@Test
	public void saveException3() throws Exception{
		try {
			QaTreeDto t = new QaTreeDto();
			t.setTitle("SRM");
			t.setIsPage("Y");
			@SuppressWarnings("unused")
			QaTreeDto d = qaTreeService.save(t);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("该标题已存在，请修改标题！"));
		}
	}
	
	//test update success
	@Test
	public void update() throws Exception{
		//save a new data first
		QaTreeDto t = new QaTreeDto();
		t.setTitle("testUpdate");
		t.setIsPage("Y");
		QaTreeDto d = qaTreeService.save(t);
		//modify the data and update
		d.setTitle("testUpdate123");
		d = qaTreeService.update(t);
		assertEquals("testUpdate123",d.getTitle());
		//remember delete the data finally
		qaTreeService.deleteById(d.getTreeId());
	}
	
	//test update ,null
	@Test
	public void updateException() throws Exception{
		try {
			@SuppressWarnings("unused")
			QaTreeDto d = qaTreeService.update(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入对象为空！"));
		}	
	}
	
	//test update ,no id
	@Test
	public void updateException2() throws Exception{
		try {
			QaTreeDto t = new QaTreeDto();
			t.setTitle("testUpdate2");
			t.setIsPage("Y");
			@SuppressWarnings("unused")
			QaTreeDto d = qaTreeService.update(t);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入对象无主键！"));
		}	
	}
	
	//test update ,node change to page while this node has child
	@Test
	public void updateException3() throws Exception{
		try {
			QaTreeDto d = qaTreeService.findById(1);
			d.setIsPage("Y");
			@SuppressWarnings("unused")
			QaTreeDto d2 = qaTreeService.update(d);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("该节点含有子集，不允许设为知识页！"));
		}	
	}
	
	//test update ,not exist
	@Test
	public void updateException4() throws Exception{
		try {
			QaTreeDto t = new QaTreeDto();
			t.setTitle("testUpdate4");
			t.setIsPage("Y");
			t.setTreeId(-11);
			@SuppressWarnings("unused")
			QaTreeDto d2 = qaTreeService.update(t);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入对象在数据库中不存在，更新失败！"));
		}	
	}
	
	//test delete success
	@Test
	public void deleteById() throws Exception{
		//insert first
		QaTreeDto t = new QaTreeDto();
		t.setTitle("testDelete");
		t.setIsPage("Y");
		QaTreeDto d = qaTreeService.save(t);
		//delete
		qaTreeService.deleteById(d.getTreeId());
		//check delete success
		d = qaTreeService.findByTitle("testDelete");
		assertNull(d);
	}
	
	//test delete ,null
	@Test
	public void deleteByIdException() throws Exception{
		try {
			qaTreeService.deleteById(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("id is null,delete fail"));
		}	
	}
	
	//test delete ,while has child
	@Test
	@Ignore
	public void deleteByIdException2() throws Exception{
		try {
			qaTreeService.deleteById(276);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("该节点含有子集，不允许删除！"));
		}	
	}
	
	//test delete ,not exist
	@Test
	public void deleteByIdException3() throws Exception{
		try {
			qaTreeService.deleteById(-276);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("要删除的主键在数据库里不存在，删除失败！"));
		}	
	}
	
	//test find with children by title ,exist
	@Test
	public void findChildrenByTitle() throws Exception{
		QaTreeDto d = qaTreeService.findChildrenByTitle("SRM");
		assertTrue(d.getChild().size()>0);
		for(QaTreeSimpleDto t:d.getChild()) {
			assertEquals(new Integer(1),t.getParentId());
		}
	}
	
	//test find with children by title ,not exist
	@Test
	public void findChildrenByTitle2() throws Exception{
		QaTreeDto d = qaTreeService.findChildrenByTitle("SRM123123");
		assertNull(d);
	}
	
	//test find with children by title,null
	@Test
	public void deleteByIdExceptionException() throws Exception{
		try {
			qaTreeService.findChildrenByTitle(null);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("title is null"));
		}	
	}

	//测试输入关键字查询结果正确
	@Test
	public void findByTitleOrKeyword() throws Exception{
		List<QaTreeSimpleDto> l = qaTreeService.findByTitleOrKeyword("srm 供应商管理 关系管理系统");
		assertTrue(l.size()>0);
	}
	
	//测试输入关键字查询结果正确
	@Test
	public void findByTitleOrKeyword2() throws Exception{
		List<QaTreeSimpleDto> l = qaTreeService.findByTitleOrKeyword("供应商关系管理系统123");
		assertEquals(0,l.size());
	}
	
	//测试输入关键字为空字符串报错
	@Test
	public void findByTitleOrKeywordException() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			List<QaTreeSimpleDto> l = qaTreeService.findByTitleOrKeyword("");
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入关键字为空！"));
		}
	}
	
	//测试输入关键字为空格报错
	@Test
	public void findByTitleOrKeywordException2() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			List<QaTreeSimpleDto> l = qaTreeService.findByTitleOrKeyword(" ");
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入关键字为空！"));
		}
	}
	
	//测试输入关键字为null报错
	@Test
	public void findByTitleOrKeywordException3() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			List<QaTreeSimpleDto> l = qaTreeService.findByTitleOrKeyword(" ");
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入关键字为空！"));
		}
	}
	
	//test find paged by keyword,exist
	@Test
	public void findPagedByTitleOrKeyword() throws QaTreeException{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("供应商关系管理系统",0,10);
		assertTrue(p.getList().size()>=1);
		assertTrue(p.getTotalElements()>=1);
		p = qaTreeService.findPagedByTitleOrKeyword("供应商关系管理系统",1,10);
		assertTrue(p.getList().size()==0);
	}
	
	//test find paged by keyword,not exist
	@Test
	public void findPagedByTitleOrKeyword2() throws QaTreeException{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findPagedByTitleOrKeyword("供应商关系管理系统123123",0,10);
		assertTrue(p.getList().size()==0);
		assertTrue(p.getTotalElements()==0);
	}
	
	//测试输入关键字为null报错
	@Test
	public void findPagedByTitleOrKeywordException() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			QaPagedDto<QaTreeSimpleDto> p  = qaTreeService.findPagedByTitleOrKeyword(" ",0,10);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("传入关键字为空！"));
		}
	}
	
	//测试输入负数
	@Test
	public void findPagedByTitleOrKeywordException2() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			QaPagedDto<QaTreeSimpleDto> p  = qaTreeService.findPagedByTitleOrKeyword("供应商关系管理系统",-1,10);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("当前页数不能小于0！"));
		}
	}
	
	//测试输入负数
	@Test
	public void findPagedByTitleOrKeywordException3() throws QaTreeException{
		try {
			@SuppressWarnings("unused")
			QaPagedDto<QaTreeSimpleDto> p  = qaTreeService.findPagedByTitleOrKeyword("供应商关系管理系统",0,-10);
			fail("expected a QaTreeException to be throw");
		}catch(QaTreeException e) {
			assertThat(e.getMessage(),is("每页条目数不能小于1！"));
		}
	}
		
	
	//test find top rank ,success
	@Test
	public void findTopRank()throws QaTreeException{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findTopRank(50);
		assertEquals(50, p.getList().size());
	}
	
	//test find top rank ,null
	@Test
	public void findTopRank2()throws QaTreeException{
		QaPagedDto<QaTreeSimpleDto> p = qaTreeService.findTopRank(null);
		assertEquals(100, p.getList().size());
	}
	
	@Test
	public void searchRecord()throws QaTreeException{
		QaTreeDto t = new QaTreeDto();
		t.setTitle("testSearchRecord");
		t.setIsPage("Y");
		t.setRank(0);
		t = qaTreeService.save(t);
		try {
			qaTreeService.searchRecord("testSearchRecord");
			//because searchRecord is a async function,so i have to sleep 1000MS
			Thread.sleep(1000);
			t = qaTreeService.findByTitle("testSearchRecord");
			assertEquals(new Integer(1),t.getRank());	
		}catch(Exception e) {
			
		}finally {
			qaTreeService.deleteById(t.getTreeId());
		}
	}
	
	@Test
	public void searchRecord2()throws QaTreeException{
		qaTreeService.searchRecord(null);
	}
}
