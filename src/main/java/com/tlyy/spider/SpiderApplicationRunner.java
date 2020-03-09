package com.tlyy.spider;

import com.tlyy.spider.dao.MemoryContainer;
import com.tlyy.spider.task.GetOriginalDataTask;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.util.concurrent.locks.StampedLock;

/**
 * @author LeiDongxing
 * created on 2020/2/13
 */
@Component
@RequiredArgsConstructor
@MapperScan(basePackages = {"com.tlyy.spider.mapper"})
@Order(value = 1)
public class SpiderApplicationRunner implements ApplicationRunner {
    private final GetOriginalDataTask task;
    private final MemoryContainer memoryContainer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memoryContainer.init();
        task.run( "368248830");
    }
}
