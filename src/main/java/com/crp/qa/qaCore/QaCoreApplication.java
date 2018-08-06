package com.crp.qa.qaCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.crp.qa.qaCore.util.counter.QaCountSaver;

@SpringBootApplication
@ServletComponentScan
public class QaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaCoreApplication.class, args);
		
		// 定期保存访问计数的线程
		QaCountSaver countSaver = QaCountSaver.getInstance();
		Thread saveThread = new Thread(countSaver);
		saveThread.start();
	}
}
