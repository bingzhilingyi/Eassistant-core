/**
 * huangyue
 * 2018年5月23日
 */
package com.crp.qa.qaCore.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crp.qa.qaCore.domain.pojo.QaTree;

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
	 * 根据title和domain查询
	 * @param title
	 * @param domain
	 * @return
	 * @Date 2018年7月21日
	 * @author huangyue
	 */
	public QaTree findByTitleAndDomainIn(String title,List<String> domain);
	
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
	 * 判断某节点domain是否存在
	 * @param domain
	 * @return
	 * @Date 2018年7月21日
	 * @author huangyue
	 */
	public boolean existsByDomain(String domain);
	
	/**
	 * 根据rank排名查找数据
	 * @author huangyue
	 * @date 2018年6月6日 上午9:31:50
	 * @param pageable
	 * @return
	 */
	public Page<QaTree> findAllByOrderByRankDesc(Pageable pageable);
	
	public Page<QaTree> findAllByDomainInOrderByRankDesc(Collection<String> domain,Pageable pageable);
	
	
	static final String FIND_BY_KEYWORD_BASESQL = "FROM QaTree qt"
			+ " WHERE 1=1 "
			+" and concat(IFNULL(qt.title,''),IFNULL(qt.label1,''),IFNULL(qt.label2,''),IFNULL(qt.label3,''),IFNULL(qt.label4,''),IFNULL(qt.label5,''))  like %?1% ";
	
	static final String FIND_BY_KEYWORD_BASESQL_2 = " and qt.domain in (?2)";
	/**
	 * 根据关键字查询知识页
	 * @param groupName
	 * @param pageable
	 * @return
	 * @Date 2018年7月10日
	 * @author huangyue
	 */
	@Query(value = "SELECT  qt " + FIND_BY_KEYWORD_BASESQL)
	List<QaTree> findByTitleOrKeyword(String keyword);
	
	@Query(value = "SELECT  qt " + FIND_BY_KEYWORD_BASESQL,
		    countQuery = "SELECT count(1) " + FIND_BY_KEYWORD_BASESQL)
	Page<QaTree> findPagedByTitleOrKeyword(String keyword,Pageable pageable);
	
	@Query(value = "SELECT  qt " + FIND_BY_KEYWORD_BASESQL + FIND_BY_KEYWORD_BASESQL_2,
		    countQuery = "SELECT count(1) " + FIND_BY_KEYWORD_BASESQL + FIND_BY_KEYWORD_BASESQL_2)
	Page<QaTree> findPagedByTitleOrKeywordAndDomain(String keyword,List<String> domain,Pageable pageable);
	
}
