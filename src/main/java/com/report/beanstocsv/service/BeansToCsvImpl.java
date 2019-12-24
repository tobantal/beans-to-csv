package com.report.beanstocsv.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.report.beanstocsv.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeansToCsvImpl implements BeansToCsv {

    private ColumnPositionMappingStrategy<User> mappingStrategy;
    private String[] columns;

    private static final int SUBREPORT_SIZE = 100_000;

    @PostConstruct
    private void init() {
        // Create Mapping Strategy to arrange the
        // column name in order
        mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(User.class);

        // Arrange column name as provided in below array.
        columns = new String[] { "id", "field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8",
                "field9", "field10", "field11", "field12", "field13" };
        mappingStrategy.setColumnMapping(columns);
    }

    @Override
    public void saveReport(List<User> users, String csvFile) {
        long startReport = System.currentTimeMillis();
        log.info("Generating REPORT");
        final int rows = users.size();
        int subreports = rows / SUBREPORT_SIZE;
        if (rows % SUBREPORT_SIZE > 0) {
            ++subreports;
        }
        for (int i = 0; i < subreports; i++) {
            saveSubReport(users.subList(i * SUBREPORT_SIZE, (i + 1) * SUBREPORT_SIZE), csvFile,
                    String.format("<%d>", i));
        }
        double duration = (System.currentTimeMillis() - startReport) / 1_000d;
        log.info("REPORT with {} rows has bean generated for {} seconds", rows, duration);
    }

    @Override
    public void saveSubReport(List<User> users, String csvFile, String subReportTag) {
        final int rows = users.size();
        long startReport = System.currentTimeMillis();
        log.info("Generating subreport {}...", subReportTag);
        try (FileWriter writer = new FileWriter(csvFile, true)) {

            long start = System.currentTimeMillis();
            log.info("Creating StatefulBeanToCsv object");
            StatefulBeanToCsvBuilder<User> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<User> beanWriter = builder.withMappingStrategy(mappingStrategy).build();
            double duration = (System.currentTimeMillis() - start) / 1_000d;
            log.info("StatefulBeanToCsv object with {} rows has bean created for {} seconds", users.size(), duration);

            log.info("Write list to StatefulBeanToCsv object");
            start = System.currentTimeMillis();
            beanWriter.write(users);
            duration = (System.currentTimeMillis() - start) / 1_000d;
            log.info("Report with {} rows has bean generated for {} seconds", rows, duration);

        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            log.error(e.getMessage());
        }
        log.info("SubReport with {} rows has been build for {} seconds", rows,
                (System.currentTimeMillis() - startReport) / 1_000d);
    }

}
