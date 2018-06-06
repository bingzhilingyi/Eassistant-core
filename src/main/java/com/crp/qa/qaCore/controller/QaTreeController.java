/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaCore.service.inte.QaTreeService;
import com.crp.qa.qaCore.util.exception.QaTreeException;
import com.crp.qa.qaCore.util.transfer.QaBaseTransfer;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;
import com.crp.qa.qaCore.util.transfer.QaPagedTransfer;

/**
 * @author huangyue
 * @date 2018年5月23日 上午11:39:07
 * @ClassName QaTreeController
 */
@RestController
@RequestMapping(path="/tree")
public class QaTreeController extends BaseController{
	
	@Resource(name="qaTreeService")
	QaTreeService qaTreeService;
	
	/**
	 * 获取所有根节点
	 * @author huangyue
	 * @date 2018年5月24日 下午4:30:04
	 * @param token
	 * @return
	 */
	@GetMapping(path="/getRoot")
	public QaBaseTransfer findRoot(){
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			List<QaTreeSimpleDto> l = qaTreeService.findRoot();
			dto.setContent(l);
		} catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据父级id查找子级
	 * @author huangyue
	 * @date 2018年5月23日 下午4:13:56
	 * @param token
	 * @param parentId
	 * @return
	 */
	@GetMapping(path="/getByParentId/{parentId}")
	public QaBaseTransfer findByParentId(@PathVariable(value="parentId") Integer parentId){
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			List<QaTreeSimpleDto> l = qaTreeService.findByParentId(parentId);
			dto.setContent(l);
		} catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 通过id找到节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:11
	 * @param token
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getById/{Id}")
	public QaBaseTransfer findById(@PathVariable(value="Id") Integer id) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			QaTreeDto d = qaTreeService.findById(id);
			dto.setContent(d);
		} catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据title精确查找
	 * @author huangyue
	 * @date 2018年5月31日 下午2:40:14
	 * @param token
	 * @param title
	 * @return
	 */
	@GetMapping(path="/getByTitle")
	public QaBaseTransfer findByTitle(@RequestParam(value="title") String title) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			QaTreeDto d = qaTreeService.findByTitle(title);
			dto.setContent(d);
		} catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 通过标题模糊查询
	 * @author huangyue
	 * @date 2018年5月31日 上午11:29:54
	 * @param token
	 * @param title
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(path="/getPagedByTitleLike")
	public QaPagedTransfer findByTitleLike(
			@RequestParam(value="title") String title,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功");
		try {
			QaPagedDto<QaTreeSimpleDto> d = qaTreeService.findPagedByTitleLike(title, page, size);
			dto.setCt(d.getList());
			dto.setContent(d.getList());
			dto.setTotalElements(d.getTotalElements());
			dto.setTotalPages(d.getTotalPages());
		} catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 保存节点信息
	 * @author huangyue
	 * @date 2018年5月28日 下午2:06:34
	 * @param value
	 * @param node
	 * @return
	 */
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="node") String node){
		QaBaseTransfer dto = new QaBaseTransfer("success","保存成功！");
		QaTreeDto d = JSONObject.parseObject(node, QaTreeDto.class);
		try {
			QaTreeDto userDto = qaTreeService.save(d);
			dto.setContent(userDto);
		}catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 更新节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:38
	 * @param token
	 * @param node
	 * @return
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(HttpEntity<String> req){
		JSONObject json = JSONObject.parseObject(req.getBody());
		String node = json.get("node").toString();
		QaBaseTransfer dto = new QaBaseTransfer("success","保存成功！");
		QaTreeDto d = JSONObject.parseObject(node, QaTreeDto.class);
		try {
			QaTreeDto userDto = qaTreeService.update(d);
			dto.setContent(userDto);
		}catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 删除节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:45
	 * @param token
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/delete/{id}")
	public QaBaseTransfer delete(@PathVariable(value="id") Integer id){
		QaBaseTransfer dto = new QaBaseTransfer("success","删除成功！");
		try {
			qaTreeService.deleteById(id);
		}catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}

	/**
	 * 根据title查找自身及子集
	 * @author huangyue
	 * @date 2018年6月6日 上午9:36:40
	 * @param title
	 * @return
	 */
	@GetMapping(path="/findChildrenByTitle")
	public QaBaseTransfer findChildrenByTitle(@RequestParam(value="title") String title) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			QaTreeDto d =qaTreeService.findChildrenByTitle(title);
			dto.setContent(d);
		}catch (QaTreeException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 找到排名最高的节点
	 * @author huangyue
	 * @date 2018年6月6日 上午9:40:53
	 * @param size
	 * @return
	 */
	@GetMapping(path="/findTopRank")
	public QaPagedTransfer findTopRank(@RequestParam(value="size") Integer size) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功！");
		try {
			QaPagedDto<QaTreeSimpleDto> d = qaTreeService.findTopRank(size);
			dto.setContent(d.getList());
			dto.setTotalElements(d.getTotalElements());
			dto.setTotalPages(d.getTotalPages());
		} catch (QaTreeException e) {
			this.returnError(e, dto);
		}
		return dto;
	}
}
