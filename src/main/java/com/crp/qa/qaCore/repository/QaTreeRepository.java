/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crp.qa.qaCore.domain.domain.QaTree;

/**
 * @author huangyue
 * @date 2018年5月23日 上午9:16:23
 * @ClassName QaTreeRepository
 */
public interface QaTreeRepository extends JpaRepository<QaTree,Integer>{

	/**
	 * 根据parentId查询信息
	 * @author huangyue
	 * @date 2018年5月23日 上午9:31:02
	 * @param parentId
	 * @return
	 */
	public List<QaTree> findByParentId(Integer parentId);
	/**
	 * 根据title精确查询
	 * @author huangyue
	 * @date 2018年5月23日 上午9:50:38
	 * @param title
	 * @return
	 */
	public QaTree findByTitle(String title);
	
	/**
	 * 根据title模糊查询
	 * @author huangyue
	 * @date 2018年5月23日 上午9:51:45
	 * @param title
	 * @return
	 */
	public Page<QaTree> findByTitleContaining(String title,Pageable pageable);
	
	/**
	 * 判断某title是否已经存在
	 * @author huangyue
	 * @date 2018年5月29日 下午4:25:47
	 * @param title
	 * @return
	 */
	public boolean existsByTitle(String title);
	
	/**
	 * 判断某个节点有无子集
	 * @author huangyue
	 * @date 2018年5月29日 下午4:39:20
	 * @param patentId
	 * @return
	 */
	public boolean existsByParentId(Integer patentId);
	
	/**
	 * 根据rank排名查找数据
	 * @author huangyue
	 * @date 2018年6月6日 上午9:31:50
	 * @param pageable
	 * @return
	 */
	public Page<QaTree> findAllByOrderByRankDesc(Pageable pageable);
}
