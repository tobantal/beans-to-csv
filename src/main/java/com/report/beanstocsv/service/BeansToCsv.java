package com.report.beanstocsv.service;

import java.util.List;

import com.report.beanstocsv.model.User;

public interface BeansToCsv {

    void saveReport(List<User> users, String csvFile);

    void saveSubReport(List<User> users, String csvFile, String subReportTag);

}
