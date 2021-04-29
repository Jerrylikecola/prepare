package com.jerrylikecola.prepare.sjms;

/**
 * @author xiaxiang
 * @date 2021/3/31 15:19
 * @description
 */
public class Singleton {

    // 一开始就创建了实例
    private static volatile Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
