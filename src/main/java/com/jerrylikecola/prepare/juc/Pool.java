package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaxiang
 * @date 2021/2/19 11:15
 * @description
 */

public class Pool {

    @Test
    public void test(){
        ExecutorService pool = new ThreadPoolExecutor(2,3,4,TimeUnit.SECONDS,new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 10; i++) {
            pool.execute(new Print(i));
        }
    }

    class Print extends Thread{
        private int str;

        public Print(int str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println(str);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
