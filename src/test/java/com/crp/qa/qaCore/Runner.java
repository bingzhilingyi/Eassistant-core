package com.crp.qa.qaCore;

import java.util.Date;

import com.crp.qa.qaCore.util.counter.QaCounter;

public class Runner implements Runnable{
	private Date date = null;
	
	public Runner() {
		this.date = new Date();
	}
	
	public Runner(Date date) {
		this.date  = date;
	}
	
	@Override
	public void run() {
		QaCounter counter = QaCounter.getInstance();
		counter.count(date);
	}

}
