package com.crp.qa.qaCore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.crp.qa.qaCore.util.counter.QaCounter;


public class TestMain {
	
	
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date d;
		try {
			d = sdf.parse("1000-01-01");
			int i = 10;
			while(i>0) {
				i--;
				Runner runner = new Runner(d);
				Thread t = new Thread(runner);
				t.start();
			}
			QaCounter counter = QaCounter.getInstance();
			System.out.println(counter.getCount(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
