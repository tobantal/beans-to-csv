package com.report.beanstocsv.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.report.beanstocsv.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDaoImpl implements UserDao {

    private static final int ROWS = 10_000_000;
    
    private static final String FAKE_DATA = UUID.randomUUID().toString();

    private User generateFakeUserById(int id) {
        return new User(id, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
    
    private User generateFakeUserById2(int id) {
        return new User(id, FAKE_DATA, FAKE_DATA, FAKE_DATA,
                FAKE_DATA, FAKE_DATA, FAKE_DATA,
                FAKE_DATA, FAKE_DATA, FAKE_DATA,
                FAKE_DATA, FAKE_DATA, FAKE_DATA,
                FAKE_DATA, FAKE_DATA, FAKE_DATA);
    }

    @Override
    public List<User> getAllUsers() {
        long start = System.currentTimeMillis();
        log.info("Generating fake users ...");
        final List<User> users = new ArrayList<>(ROWS);
        for (int i = 1; i <= ROWS; i++) {
            users.add(generateFakeUserById2(i));
        }
        double duration = (System.currentTimeMillis() - start)/1_000d;
        log.info("{} fake users have bean generated for {} seconds", ROWS, duration);
        return users;
    }

}
