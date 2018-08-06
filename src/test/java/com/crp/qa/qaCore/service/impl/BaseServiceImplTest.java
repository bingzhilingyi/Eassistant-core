package com.crp.qa.qaCore.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BaseServiceImplTest {
	
	@SuppressWarnings("rawtypes")
	BaseServiceImpl baseService = new BaseServiceImpl();
	

	@Test
	public void checkNull() {
		try {
			List<String> l = new ArrayList<String>();
			baseService.checkNull(l, "l is empty");
			fail("expected a NullPointerException to be throwed");
		}catch(NullPointerException e) {
			assertEquals("l is empty",e.getMessage());
		}
	}


}
