package com.crp.qa.qaCore.util.counter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crp.qa.qaCore.domain.dto.QaSearchCountDto;
import com.crp.qa.qaCore.service.inte.QaSearchCountService;
import com.crp.qa.qaCore.util.exception.QaSearchCountException;

@Component
public class QaCounter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(QaCounter.class);

	private static QaCounter counter = null;
	
	private Map<String,Integer> count = new HashMap<String,Integer>();
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	QaSearchCountService qaSearchCountService;
	
	private QaCounter() {}
	
	@PostConstruct
    public void init() {
		counter = this;
		counter.qaSearchCountService = this.qaSearchCountService;
    }
	
	public static QaCounter getInstance() {
		if(counter == null) {
			synchronized (QaCounter.class) {
				if(counter == null) {
					counter = new QaCounter();
				}
			}
		}
		return counter;
	}
	
	/**
	 * 计数
	 * @return
	 * @Date 2018年8月3日
	 * @author huangyue
	 */
	public Integer count(){
		return count(new Date());
	}
	public Integer count(Date date){
		if(date == null) {
			return null;
		}
		String today = formatter.format(date);
		
		Integer oriCount;
		synchronized(this) {
			oriCount = count.get(today)==null?0:count.get(today);
			count.put(today, oriCount+1);
		}
		
		return oriCount;
	}
	
	/**
	 * 获取某天计数,默认是当天
	 * @param date
	 * @return
	 * @Date 2018年8月3日
	 * @author huangyue
	 */
	public Integer getCount() {
		return getCount(new Date());
	}
	public Integer getCount(Date date) {
		if(date == null) {
			return null;
		}
		String dateStr = formatter.format(date);
		return count.get(dateStr);
	}
	
	/**
	 * 删除某天的计数，默认当天
	 * @param date
	 * @return
	 * @Date 2018年8月3日
	 * @author huangyue
	 */
	public Integer delCount() {
		return delCount(new Date());
	}
	public Integer delCount(Date date) {
		if(date == null) {
			return null;
		}
		String dateStr = formatter.format(date);
		Integer num = count.get(dateStr);
		count.remove(dateStr);
		return num;
	}
	
	/**
	 * 保存某日的数据
	 * @param date
	 * @param num
	 * @Date 2018年8月3日
	 * @author huangyue
	 */
	public void saveOrUpdate(Date date,Integer num) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			QaSearchCountDto count = qaSearchCountService.findByDate(date);
			if(count!=null) {
				//如果中途重启过应用，计数器会清空，可能导致计数器小于数据库里的值，这时就把数据库里的数据作为原始数据
				//如果计数器大于数据库的值，就忽略之前丢失的数据
				//当然可以用redis作为临时缓存，这样比较精确，这里没有使用
				if(count.getCountNumber()<num) {
					num += count.getCountNumber();
				}
				count.setCountNumber(num);
				qaSearchCountService.update(count);
			}else {
				count = new QaSearchCountDto(sdf.format(date),num);
				qaSearchCountService.save(count);
			}
		} catch (NullPointerException | QaSearchCountException e) {
			LOGGER.error("保存访问计数失败！",e);
		}
	}
}
