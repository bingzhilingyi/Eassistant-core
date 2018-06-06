/**
 * huangyue
 * 2018年5月22日
 */
package com.crp.qa.qaCore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangyue
 * @date 2018年5月22日 下午2:11:39
 * @ClassName CommonFilter
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "commonFilter")
public class CommonFilter implements Filter {
	private final Logger LOGGER = LoggerFactory.getLogger(CommonFilter.class);
	
	@Value("${TOKEN.CORE}")
	private String TOKEN;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 获得在下面代码中要用的request,response
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		LOGGER.info("excute start time...");
		try{
			// 获得用户请求的URI
			String path = servletRequest.getRequestURI();
			String basepath = servletRequest.getContextPath();						
			LOGGER.info("excute basepath:{},URI:{}",new Object[]{basepath,path});
			//判断token是否存在，不存在就报错
			String token = (String)servletRequest.getParameter("token");
			//以下页面不需要token即可访问
			if (path.indexOf("/error") > -1) {
				//过滤结束
		    	filterChain.doFilter(servletRequest, servletResponse);
		    	return;
			}
			//如果没有token，或token不对，重定向到错误
			if(token==null||!this.TOKEN.equals(token)) {
				servletResponse.sendRedirect(basepath + "/error/notoken");
				return;
			}
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
