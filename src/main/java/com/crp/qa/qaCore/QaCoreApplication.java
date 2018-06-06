package com.crp.qa.qaCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class QaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaCoreApplication.class, args);
	}
}
