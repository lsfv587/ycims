package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 16:15
 **/
@SpringBootApplication
@EnableTransactionManagement
public class jqycWebMain{
    public static void main(String[] args) {
        SpringApplication.run(jqycWebMain.class,args);
    }
}