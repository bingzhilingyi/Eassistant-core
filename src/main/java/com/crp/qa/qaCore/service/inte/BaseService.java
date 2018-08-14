package com.crp.qa.qaCore.service.inte;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.data.domain.Pageable;


/**
 * 基础服务接口
 * @author huangyue
 * @date 2018年5月9日 下午9:42:46
 * @ClassName QaSysUserService
 */
public interface BaseService<T> {	
	public <D> List<D> pojoToDto(Class<D> dClass,Iterable<T> origList) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException;
	public void checkNull(Object obj,String message) throws NullPointerException;
	public Pageable getPageable(Integer page, Integer size);
}
