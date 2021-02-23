package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaxiang
 * @date 2021/2/19 11:20
 * @description
 */

@Slf4j
public class Sleep implements Runnable{

    @Override
    public void run() {
        log.info(Thread.currentThread().getName()+" : I start to sleep ! ");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName()+" : I end to sleep ! ");
    }
}
