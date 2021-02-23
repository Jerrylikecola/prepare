package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaxiang
 * @date 2021/2/19 11:15
 * @description
 */

@Component
@Slf4j
public class Pool {

    private ThreadPoolExecutor pool;

    @PostConstruct
    public void init() {
        this.pool = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
    }

    public void submit(Runnable runnable) {
        pool.submit(runnable);
        log.info("activeCount = " + pool.getActiveCount() + "\npoolSize = " + pool.getPoolSize() +
                "\ncompletedTaskCount = " + pool.getCompletedTaskCount() + "\ntaskCount = " + pool.getTaskCount());
    }

    @PreDestroy
    public void destroy() {
        pool.shutdown();
    }
}
