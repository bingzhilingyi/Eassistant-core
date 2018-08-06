/**
 * huangyue
 * 2018年5月10日
 */
package com.crp.qa.qaCore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crp.qa.qaCore.service.inte.BaseService;

/**
 * 基础服务
 * @author huangyue
 * @date 2018年5月10日 下午2:26:55
 * @ClassName BaseServiceImpl
 */
public class BaseServiceImpl<T> implements BaseService<T>{
	
	final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
	public DozerBeanMapper mapper = new DozerBeanMapper();// 获取mapper
	
	@Override
	public <D> List<D> pojoToDto(Class<D> dClass,Iterable<T> origList) throws MappingException, InstantiationException, IllegalAccessException{
		List<D> destList = new ArrayList<D>();
		for(T t:origList) {
			D d = (D)dClass.newInstance();
			mapper.map(t, d);
			destList.add(d);
		}
		return destList;
	}
	
	@Override
	public void checkNull(Object o,String message) throws NullPointerException {
		if( o == null 
				|| ( o instanceof String && "".equals( (String)o ) ) 
				|| ( o instanceof List && ((List<?>)o).size()==0) ){					 
			throw new NullPointerException(message);
		}
	}
}
