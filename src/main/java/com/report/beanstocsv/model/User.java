package com.report.beanstocsv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String field1;
    
    @Column
    private String field2;
    
    @Column
    private String field3;
    
    @Column
    private String field4;
    
    @Column
    private String field5;
    
    @Column
    private String field6;
    
    @Column
    private String field7;
    
    @Column
    private String field8;
    
    @Column
    private String field9;
    
    @Column
    private String field10;
    
    @Column
    private String field11;
    
    @Column
    private String field12;
    
    @Column
    private String field13;
    
    @Column
    private String field14;
    
    @Column
    private String field15;

}
