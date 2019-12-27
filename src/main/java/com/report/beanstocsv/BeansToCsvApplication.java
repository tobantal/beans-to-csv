package com.report.beanstocsv;

import java.util.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.report.beanstocsv.dao.UserDao;
import com.report.beanstocsv.model.User;
import com.report.beanstocsv.repository.UserRepository;
import com.report.beanstocsv.service.BeansToCsv;


@SpringBootApplication
public class BeansToCsvApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BeansToCsvApplication.class, args);

        saveFile(context);

        System.exit(0);
    }

    private static void saveFile(ConfigurableApplicationContext context) {
        BeansToCsv beansToCsv = context.getBean(BeansToCsv.class);
        UserDao userDao = context.getBean(UserDao.class);
        String csvFile = String.format("report-%d.csv", System.currentTimeMillis());
        beansToCsv.saveReport(userDao.getAllUsers(), csvFile);
	}

    private static void loadBigDataFromPostgres(ConfigurableApplicationContext context) {
        UserRepository repo = context.getBean(UserRepository.class);

        long start = System.currentTimeMillis();
        Iterator<User> iterator = repo.findAll().iterator();
        long count = 0;
        while (iterator.hasNext()) {
            count++;
        }
        double duration = (System.currentTimeMillis() - start) / 1_000d;

        System.out.println(String.format(">>>>>>>>>> Import %d users for %s sec", count, duration));
    }

}
