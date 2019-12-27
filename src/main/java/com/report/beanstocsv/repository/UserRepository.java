package com.report.beanstocsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.report.beanstocsv.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
