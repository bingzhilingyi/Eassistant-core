package com.crp.qa.qaCore.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class QaSearchHistoryControllerTest {
	
	private static final String token = "8hXa5JjiJtpq6ICoIOhXqqsAHu3bgH";
	
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。  
	
	@Autowired  
    private WebApplicationContext wac; // 注入WebApplicationContext  

	@Before // 在测试开始前初始化工作  
    public void setup() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    } 

	@Test
	public void findTopRank() throws Exception{
		System.out.println("---------------findTopRank start-----------------"); 
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(
        			get("/history/findTopRank")
        			.param("token", token).param("rank", "10")
        		)
        		.andExpect(status().isOk())// 模拟向testRest发送get请求  
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8  
                .andExpect(jsonPath("$.status").value("success")) //jsonPath就是把返回的结果转换成的json
                .andExpect(jsonPath("$.content.length()").value(10))
                .andReturn();// 返回执行请求的结果  
        //System.out.println(result.getResponse().getContentAsString());  
        System.out.println("---------------findTopRank success-----------------");  
	}

}
