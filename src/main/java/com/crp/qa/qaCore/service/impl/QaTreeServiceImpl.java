/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.dozer.MappingException;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaCore.domain.pojo.QaTree;
import com.crp.qa.qaCore.repository.QaTreeRepository;
import com.crp.qa.qaCore.service.inte.QaPageService;
import com.crp.qa.qaCore.service.inte.QaSearchHistoryService;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaSearchHistoryException;
import com.crp.qa.qaCore.util.exception.QaTreeException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

/**
 * @author huangyue
 * @date 2018年5月23日 上午9:59:53
 * @ClassName QaTreeServiceImpl
 */
@Service(value="qaTreeService")
@Transactional
public class QaTreeServiceImpl extends BaseServiceImpl<QaTree> implements QaTreeService{
    
	@Autowired
	public QaTreeRepository qaTreeRepository;
	@Resource(name="qaPageService")
	public QaPageService qaPageService;
	@Resource(name="qaSearchHistoryService")
	private QaSearchHistoryService qaSearchHistoryService;
	

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#findRoot()
	 */
	@Override
	public List<QaTreeSimpleDto> findRoot() throws QaTreeException{
		List<QaTree> treeList = qaTreeRepository.findByParentId(0);
		List<QaTreeSimpleDto> dtoList = new ArrayList<QaTreeSimpleDto>();
		if(treeList.size()>0) {
			try {
				dtoList = pojoToDto(QaTreeSimpleDto.class,treeList);
				return dtoList;
			} catch (IllegalAccessException | InstantiationException | MappingException e) {
				throw new QaTreeException("pojo转dto失败",e);
			}
		}
		return dtoList;
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#findByParentId(java.lang.Integer)
	 */
	@Override
	public List<QaTreeSimpleDto> findByParentId(Integer parentId) throws QaTreeException,NullPointerException{
		checkNull(parentId,"传入的parentId为空！");
		List<QaTree> treeList = qaTreeRepository.findByParentId(parentId);
		try {
			return pojoToDto(QaTreeSimpleDto.class,treeList);
		} catch (IllegalAccessException | InstantiationException | MappingException e) {
			throw new QaTreeException("pojo转dto失败",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#findByTitle(java.lang.String)
	 */
	@Override
	public QaTreeDto findByTitle(String title) throws QaTreeException,NullPointerException{
		return findByTitle(title,null,false);
	}
	
	@Override
	public QaTreeDto findByTitle(String title,List<String> domain) throws QaTreeException,NullPointerException{
		return findByTitle(title,domain,true);
	}
	
	@Override
	public QaTreeDto findByTitle(String title,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException{
		title = title==null?"":title.trim();
		checkNull(title,"传入的title为空！");
		
		//如果是严格模式，需要判断domain
		if(strict) {
			checkNull(domain,"传入的域为空！");
		}
		
		QaTree tree;
		if(strict) {
			tree = qaTreeRepository.findByTitleAndDomainIn(title,domain);
		}else {
			tree = qaTreeRepository.findByTitle(title);
		}
		 
		if(tree!=null) {
			try {
				QaTreeDto treeDto = new QaTreeDto();
				mapper.map(tree, treeDto);
				return treeDto;
			} catch (MappingException e) {
				throw new QaTreeException("pojo转dto失败",e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#findByTitleLike(java.lang.String)
	 */
	@Override
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleLike(String title,Integer page,Integer size) throws QaTreeException,NullPointerException{
		title = title==null?"":title.trim();
		page = page==null?0:page;
		size = size==null?10:size;
		
		checkNull(title,"title is null!");
		
		//把title里的空格换成%
		title = title.replaceAll("\\s+", "%");
		
		//定义查询结果
		List<QaTree> tList = new ArrayList<QaTree>();
 				
		//设置分页信息
		Pageable pageable = PageRequest.of(page,size);
        
		//查询数据
		Page<QaTree> treePage = qaTreeRepository.findByTitleContaining(title, pageable);
		tList = treePage.getContent();
		
		//定义返回值
		QaPagedDto<QaTreeSimpleDto> returnDto = new QaPagedDto<QaTreeSimpleDto>();
		try {
			 List<QaTreeSimpleDto> dList = pojoToDto(QaTreeSimpleDto.class,tList);
			 returnDto.setList(dList);
			 returnDto.setTotalElements(treePage.getTotalElements());
			 returnDto.setTotalPages(treePage.getTotalPages());
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaTreeException("pojo转dto失败",e);
		} 
		return returnDto;
	}
	
	@Override
	public QaTreeDto findById(Integer id) throws QaTreeException,NullPointerException{
		checkNull(id,"传入的Id为空！");
		Optional<QaTree> opt = qaTreeRepository.findById(id);
		if(opt.isPresent()) {
			try {
				QaTreeDto d = new QaTreeDto();
				mapper.map(opt.get(), d);
				return d;
			} catch (MappingException e) {
				throw new QaTreeException("pojo转dto失败",e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#save(com.crp.qa.qaCore.domain.dto.QaTreeDto)
	 */
	@Override
	public QaTreeDto save(QaTreeDto d) throws QaTreeException ,NullPointerException{
		checkNull(d,"传入对象为空！");
		checkNull(d.getTitle(),"标题为空！");
		checkNull(d.getDomain(),"域为空！");
		checkNull(d.getParentId(),"父节点不存在！");
		
		if(d.getTreeId() != null) {
			throw new QaTreeException("传入对象已有主键！");
		}else if(qaTreeRepository.existsByTitle(d.getTitle())) {
			throw new QaTreeException("该标题已存在，请修改标题！");
		}else if(d.getParentId()==0 && qaTreeRepository.existsByDomain(d.getDomain())) {
			throw new QaTreeException("域已存在，请修改域！");
		}
		try {
			QaTree t = new QaTree();
			mapper.map(d, t);
			//设置日期
			t.setCreationDate(new Date());
			t.setLastUpdateDate(new Date());
			//默认为是节点
			if(t.getIsPage()==null) {
				t.setIsPage("N");
			}
			//热度默认为0
			if(t.getRank()==null) {
				t.setRank(0);
			}
			//save
			t = qaTreeRepository.saveAndFlush(t);
			mapper.map(t, d);
			return d;
		} catch (MappingException e) {
			throw new QaTreeException("pojo转dto失败！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#update(com.crp.qa.qaCore.domain.dto.QaTreeDto)
	 */
	@Override
	public QaTreeDto update(QaTreeDto d) throws QaTreeException,NullPointerException {
		checkNull(d,"传入对象为空！");
		checkNull(d.getTreeId(),"传入对象无主键！");
		
		if(d.getIsPage().equals("Y")&&qaTreeRepository.findByParentId(d.getTreeId()).size()>0) {
			throw new QaTreeException("该节点含有子集，不允许设为知识页！");
		}else if(!qaTreeRepository.existsById(d.getTreeId())) {
			throw new QaTreeException("传入对象在数据库中不存在，更新失败！");
		}
		
		try {
			QaTree t = new QaTree();
			mapper.map(d, t);
			//设置日期
			t.setLastUpdateDate(new Date());
			//热度默认为0
			if(t.getRank()==null) {
				t.setRank(0);
			}
			//如果域为空，且不是根节点，则继承他上级的域
			if(t.getDomain()==null && t.getParentId()!=null && t.getParentId()!=0) {
				Optional<QaTree> parent = qaTreeRepository.findById(t.getParentId());
				if(parent.isPresent()) {
					t.setDomain(parent.get().getDomain());
				}
			}
			//save
			t = qaTreeRepository.saveAndFlush(t);
			mapper.map(t, d);
			return d;
		} catch (MappingException e) {
			throw new QaTreeException("pojo转dto失败！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) throws QaTreeException,NullPointerException {
		checkNull(id,"id is null,delete fail");
		if(qaTreeRepository.existsByParentId(id)) {
			throw new QaTreeException("该节点含有子集，不允许删除！");
		}else if(!qaTreeRepository.existsById(id)) {
			throw new QaTreeException("要删除的主键在数据库里不存在，删除失败！");
		}
		//删除
		qaTreeRepository.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.crp.qa.qaCore.service.inte.QaTreeService#findPagedAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public QaPagedDto<QaTreeSimpleDto> findPagedAll(Integer page, Integer size) throws QaTreeException {
		//初始化参数
		page = page==null?1:page;
		size = size==null?20:size;
		//设置分页信息
		Pageable pageable = PageRequest.of(page,size);
		Page<QaTree> pagedTree = qaTreeRepository.findAll(pageable);
		try {
			List<QaTreeSimpleDto> dList = pojoToDto(QaTreeSimpleDto.class,pagedTree.getContent());
			//返回信息
			QaPagedDto<QaTreeSimpleDto> returnDto = new QaPagedDto<QaTreeSimpleDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(pagedTree.getTotalElements()); //总条目
			returnDto.setTotalPages(pagedTree.getTotalPages()); //总页数
			return returnDto;
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaTreeException("pojo转dto失败",e);
		}	
	}

	@Override
	public QaTreeDto findChildrenByTitle(String title) throws QaTreeException,NullPointerException{
		return findChildrenByTitle(title,null,false);
	}
	@Override
	public QaTreeDto findChildrenByTitle(String title,List<String> domain) throws QaTreeException,NullPointerException{
		return findChildrenByTitle(title,domain,true);
	}
	
	@Override
	public QaTreeDto findChildrenByTitle(String title,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException{
		title = title==null?"":title.trim();
		checkNull(title,"title is null");
		//严格模式判断domain是否为空
		if(strict) {
			checkNull(domain,"domain is empty");
		}
		QaTreeDto father;
		if(strict) {
			father = this.findByTitle(title,domain);
		}else {
			father = this.findByTitle(title);
		}
		if(father!=null&&father.getTreeId()!=null&&father.getIsPage().equals("N")) {
			List<QaTreeSimpleDto> childList = this.findByParentId(father.getTreeId());
			Set<QaTreeSimpleDto> child = new HashSet<QaTreeSimpleDto>(childList);
			father.setChild(child);
		}	
		return father;
	}
	
	@Override
	public List<QaTreeSimpleDto> findByTitleOrKeyword(String keyword) throws QaTreeException,NullPointerException{
		//把title里的空格换成%
		keyword = keyword==null?"":keyword.trim().replaceAll("\\s+", "%");
		checkNull(keyword,"传入关键字为空！");
		List<QaTree> treeList = qaTreeRepository.findByTitleOrKeyword(keyword);
		try {
			return pojoToDto(QaTreeSimpleDto.class,treeList);
		} catch (IllegalAccessException | InstantiationException | MappingException e) {
			throw new QaTreeException("pojo转dto失败",e);
		}
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword,Integer page,Integer size) throws QaTreeException ,NullPointerException{
		return findPagedByTitleOrKeyword(keyword,page,size,null,false);	
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword, Integer page,
			Integer size, List<String> domain) throws QaTreeException,NullPointerException {
		return findPagedByTitleOrKeyword(keyword,page,size,domain,true);
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword, Integer page,
			Integer size, List<String> domain,Boolean strict) throws QaTreeException,NullPointerException {
		//把title里的空格换成%
		keyword = keyword==null?"":keyword.trim().replaceAll("\\s+", "%");
		page = page==null?0:page;
		size = size==null?10:size;
		
		checkNull(keyword,"传入关键字为空！");
		
		if(strict) {
			checkNull(domain,"传入域为空！");
		}
		
		if(page<0) {
			throw new QaTreeException("当前页数不能小于0！");
		}else if(size<1) {
			throw new QaTreeException("每页条目数不能小于1！");
		}
		
		//设置分页信息
		Pageable pageable = PageRequest.of(page,size);
		Page<QaTree> pagedTree;
		if(strict) {
			pagedTree = qaTreeRepository.findPagedByTitleOrKeywordAndDomain(keyword,domain,pageable);
		}else {
			pagedTree = qaTreeRepository.findPagedByTitleOrKeyword(keyword,pageable);
		}
		
		try {
			List<QaTreeSimpleDto> dList = pojoToDto(QaTreeSimpleDto.class,pagedTree.getContent());
			//返回信息
			QaPagedDto<QaTreeSimpleDto> returnDto = new QaPagedDto<QaTreeSimpleDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(pagedTree.getTotalElements()); //总条目
			returnDto.setTotalPages(pagedTree.getTotalPages()); //总页数
			return returnDto;
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaTreeException("pojo转dto失败",e);
		}
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size) throws QaTreeException,NullPointerException{
		return findTopRank(size,null,false);
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size,List<String> domain) throws QaTreeException,NullPointerException{
		return findTopRank(size,domain,true);
	}
	
	@Override
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException{
		size = size==null?100:size;
		if(strict) {
			checkNull(domain,"传入域为空");
		}
		//设置分页信息，永远查第一页
		Pageable pageable = PageRequest.of(0,size);
		//查询数据
		Page<QaTree> pagedTree;
		if(strict) {
			pagedTree = qaTreeRepository.findAllByDomainInOrderByRankDesc(domain,pageable);
		}else {
			pagedTree = qaTreeRepository.findAllByOrderByRankDesc(pageable);
		}
		try {
			List<QaTreeSimpleDto> dList = pojoToDto(QaTreeSimpleDto.class,pagedTree.getContent());
			//返回信息
			QaPagedDto<QaTreeSimpleDto> returnDto = new QaPagedDto<QaTreeSimpleDto>();
			returnDto.setList(dList); //数据
			returnDto.setTotalElements(pagedTree.getTotalElements()); //总条目
			returnDto.setTotalPages(pagedTree.getTotalPages()); //总页数
			return returnDto;
		} catch (IllegalAccessException | InstantiationException e) {
			throw new QaTreeException("pojo转dto失败",e);
		}
	}

	@Override
	public QaTreeDto evaluate(Integer id,Boolean isLike) throws QaTreeException,NullPointerException {
		checkNull(id,"传入知识页id为null！");
		isLike = isLike==null?false:isLike;
		QaTree pojo = null;
		synchronized (id.toString()) {
			Optional<QaTree> dto = qaTreeRepository.findById(id);
			if(dto.isPresent()) {
				QaTree t = dto.get();
				int now = 0;
				if(isLike) {
					now = t.getLike()==null?0:t.getLike();
					t.setLike(now+1);
				}else {
					now = t.getDislike()==null?0:t.getDislike();
					t.setDislike(now+1);
				}
				pojo = qaTreeRepository.saveAndFlush(t);
			}
		}
		if(pojo!=null) {
			try {
				QaTreeDto dto = new QaTreeDto();
				mapper.map(pojo, dto);
				return dto;
			}catch(MappingException e) {
				throw new QaTreeException("pojo 转 dto 失败",e);
			}
		}
		return null;
	}	
}
