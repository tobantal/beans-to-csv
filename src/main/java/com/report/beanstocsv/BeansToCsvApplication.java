package com.report.beanstocsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.report.beanstocsv.dao.UserDao;
import com.report.beanstocsv.service.BeansToCsv;

@SpringBootApplication
public class BeansToCsvApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BeansToCsvApplication.class, args);

        BeansToCsv beansToCsv = context.getBean(BeansToCsv.class);
        UserDao userDao = context.getBean(UserDao.class);
        String csvFile = "report.csv";
        beansToCsv.saveReport(userDao.getAllUsers(), csvFile);

        System.exit(0);
	}

}
