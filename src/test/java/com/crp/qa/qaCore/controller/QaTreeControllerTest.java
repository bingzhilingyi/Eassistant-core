package com.crp.qa.qaCore.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QaTreeControllerTest {
	
private static final String token = "8hXa5JjiJtpq6ICoIOhXqqsAHu3bgH";
	
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。  
	
	@Autowired  
    private WebApplicationContext wac; // 注入WebApplicationContext  

	@Before // 在测试开始前初始化工作  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    } 

	//测试关键字查询结果正常情况
	@Test
	public void findByTitleOrKeyword() throws Exception{
		System.out.println("---------------findByTitleOrKeyword start-----------------");  
		//Map<String, Object> map = new HashMap<>(); 	
        //MvcResult result = mockMvc.perform(post("/group/findAll").content(JSONObject.toJSONString(map)))
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findByTitleOrKeyword")
        			.param("token", token)
        			.param("keyword", "供应商管理 供应商关系")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("success")) //jsonPath就是把返回的结果转换成的json
                .andExpect(jsonPath("$.content[?(@.treeId==1)].pageId").value(4))
                .andReturn();// 返回执行请求的结果  
        //System.out.println(result.getResponse().getContentAsString());  
        System.out.println("---------------findByTitleOrKeyword success-----------------");          
	}
	
	//测试关键字查询结果不存在情况
	@Test
	public void findByTitleOrKeywordNone() throws Exception{
		System.out.println("---------------findByTitleOrKeywordNone start-----------------");
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findByTitleOrKeyword")
        			.param("token", token)
        			.param("keyword", "lawdawd")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("success")) //jsonPath就是把返回的结果转换成的json
                .andExpect(jsonPath("$.content.length()").value(0))
                .andReturn();// 返回执行请求的结果 
        System.out.println("---------------findByTitleOrKeywordNone success-----------------");          
	}

	//测试关键字查询结果失败情况
	@Test
	public void findByTitleOrKeywordFail() throws Exception{
		System.out.println("---------------findByTitleOrKeywordFail start-----------------");
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findByTitleOrKeyword")
        			.param("token", token)
        			.param("keyword", "")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("failed")) //jsonPath就是把返回的结果转换成的json
                .andReturn();// 返回执行请求的结果 
        System.out.println("---------------findByTitleOrKeywordFail success-----------------");          
	}
	
	//测试paged关键字查询结果正常情况
	@Test
	public void findPagedByTitleOrKeyword() throws Exception{
		System.out.println("---------------findPagedByTitleOrKeyword start-----------------"); 
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findPagedByTitleOrKeyword")
        			.param("token", token).param("keyword", "供应商管理 供应商关系")
        			.param("page", "0").param("size", "10")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("success")) //jsonPath就是把返回的结果转换成的json
                .andExpect(jsonPath("$.content[?(@.treeId==1)].pageId").value(4))
                .andReturn();// 返回执行请求的结果  
        //System.out.println(result.getResponse().getContentAsString());  
        System.out.println("---------------findPagedByTitleOrKeyword success-----------------");          
	}
	
	//测试paged关键字查询结果正常情况
	@Test
	public void findPagedByTitleOrKeyword2() throws Exception{
		System.out.println("---------------findPagedByTitleOrKeyword start-----------------"); 
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findPagedByTitleOrKeyword")
        			.param("token", token).param("keyword", "devops")
        			.param("page", "1").param("size", "10")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("success")) //jsonPath就是把返回的结果转换成的json
                .andExpect(jsonPath("$.content.size()").value(0))
                .andReturn();// 返回执行请求的结果  
        //System.out.println(result.getResponse().getContentAsString());  
        System.out.println("---------------findPagedByTitleOrKeyword success-----------------");          
	}
	
	//测试paged关键字查询结果,exception
	@Test
	public void findPagedByTitleOrKeywordException() throws Exception{
		System.out.println("---------------findPagedByTitleOrKeyword start-----------------"); 
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/tree/findPagedByTitleOrKeyword")
        			.param("token", token).param("keyword", "")
        			.param("page", "1").param("size", "10")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("failed")) //jsonPath就是把返回的结果转换成的json
                .andReturn();// 返回执行请求的结果  
        System.out.println("---------------findPagedByTitleOrKeyword success-----------------");          
	}
}
