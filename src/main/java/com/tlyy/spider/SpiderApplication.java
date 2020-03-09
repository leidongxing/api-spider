package com.tlyy.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LeiDongxing
 * created on 2020/2/8
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.tlyy.spider.mapper"})
public class SpiderApplication {
     public static void main(String[] args){
         SpringApplication.run(SpiderApplication.class, args);
     }
}
