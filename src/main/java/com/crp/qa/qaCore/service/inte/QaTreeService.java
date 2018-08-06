/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.service.inte;

import java.util.List;

import com.crp.qa.qaCore.domain.dto.QaTreeDto;
import com.crp.qa.qaCore.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaCore.domain.pojo.QaTree;
import com.crp.qa.qaCore.util.exception.QaTreeException;
import com.crp.qa.qaCore.util.transfer.QaPagedDto;

/**
 * 知识页层级服务
 * @author huangyue
 * @date 2018年5月23日 上午9:20:13
 * @ClassName QaTreeService
 */
public interface QaTreeService extends BaseService<QaTree>{

	/**
	 * 查找所有跟节点
	 * @author huangyue
	 * @date 2018年5月23日 上午9:56:44
	 * @return
	 */
	public List<QaTreeSimpleDto> findRoot() throws QaTreeException;
	
	/**
	 * 查找分页后的所有节点
	 * @author huangyue
	 * @date 2018年5月30日 下午8:19:47
	 * @param page
	 * @param size
	 * @return
	 * @throws QaTreeException
	 */
	public QaPagedDto<QaTreeSimpleDto> findPagedAll(Integer page,Integer size) throws QaTreeException;
	
	/**
	 * 根据parentid查找所有子节点
	 * @author huangyue
	 * @date 2018年5月23日 上午9:57:25
	 * @param parentId
	 * @return
	 */
	public List<QaTreeSimpleDto> findByParentId(Integer parentId) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据title精确查询节点
	 * @param title 标题
	 * @param domain 域
	 * @param strict 是否严格
	 * @author huangyue
	 * @date 2018年5月23日 上午9:58:06
	 * @param title
	 * @return
	 */
	public QaTreeDto findByTitle(String title) throws QaTreeException,NullPointerException;
	
	public QaTreeDto findByTitle(String title,List<String> domain) throws QaTreeException,NullPointerException;
	
	public QaTreeDto findByTitle(String title,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据title模糊查询节点
	 * @author huangyue
	 * @date 2018年5月23日 上午9:58:51
	 * @param title
	 * @return
	 */
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleLike(String title,Integer page,Integer size) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据id查找节点
	 * @author huangyue
	 * @date 2018年5月28日 上午9:15:09
	 * @param Id
	 * @return
	 * @throws QaTreeException
	 */
	public QaTreeDto findById(Integer Id) throws QaTreeException,NullPointerException;
	
	/**
	 * 保存
	 * @author huangyue
	 * @date 2018年5月28日 下午1:59:03
	 * @param d
	 * @return
	 * @throws QaTreeException
	 */
	public QaTreeDto save(QaTreeDto d) throws QaTreeException,NullPointerException;
	
	/**
	 * 更新
	 * @author huangyue
	 * @date 2018年5月28日 下午2:09:32
	 * @param d
	 * @return
	 * @throws QaTreeException
	 */
	public QaTreeDto update(QaTreeDto d) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据id删除
	 * @author huangyue
	 * @date 2018年5月28日 下午2:15:14
	 * @param id
	 * @throws QaTreeException
	 */
	public void deleteById(Integer id) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据title查找该节点及其子节点
	 * @author huangyue
	 * @date 2018年6月1日 下午4:39:09
	 * @param title
	 * @return
	 * @throws QaTreeException
	 */
	public QaTreeDto findChildrenByTitle(String title) throws QaTreeException,NullPointerException;
	
	public QaTreeDto findChildrenByTitle(String title,List<String> domain) throws QaTreeException,NullPointerException;
	
	public QaTreeDto findChildrenByTitle(String title,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据keyword查找节点
	 * @param keyword
	 * @return
	 * @throws QaTreeException
	 * @Date 2018年7月17日
	 * @author huangyue
	 */
	public List<QaTreeSimpleDto> findByTitleOrKeyword(String keyword) throws QaTreeException,NullPointerException;
	
	/**
	 * 根据keyword查找分页的节点
	 * @param keyword 查询条件
	 * @param page 当前页
	 * @param size 每页大小
	 * @param domain 域
	 * @param strict 是否严格模式
	 * @return
	 * @throws QaTreeException
	 * @Date 2018年7月19日
	 * @author huangyue
	 */
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword,Integer page,Integer size) throws QaTreeException,NullPointerException;
	
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword,Integer page,Integer size,List<String> domain) throws QaTreeException,NullPointerException;
	
	public QaPagedDto<QaTreeSimpleDto> findPagedByTitleOrKeyword(String keyword,Integer page,Integer size,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException;
	
	/**
	 * 以rank值排序查找指定数量的节点
	 * @author huangyue
	 * @param size size of top rank
	 * @param domain
	 * @param strict
	 * @return
	 * @throws QaTreeException
	 */
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size) throws QaTreeException,NullPointerException;
	
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size,List<String> domain) throws QaTreeException,NullPointerException;
	
	public QaPagedDto<QaTreeSimpleDto> findTopRank(Integer size,List<String> domain,Boolean strict) throws QaTreeException,NullPointerException;
	
	
}
