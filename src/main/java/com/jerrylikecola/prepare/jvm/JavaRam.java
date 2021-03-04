package com.jerrylikecola.prepare.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author xiaxiang
 * @date 2021/2/24 13:42
 * @description Java内存区域
 */
public class JavaRam {
    /**
     * >>>JVM 运行时数据区域<<<
     * --------------------------------------------
     * ｜   方法区   ｜ 虚拟机栈 ｜     本地方法栈     ｜
     * ｜Method Area｜VM Stack｜Native Method Stack｜
     * ｜       堆      ｜         程序计数器        ｜
     * ｜     Heap      ｜ Program Counter Register｜
     * --------------------------------------------
     * ｜｜               ｜｜
     * 执行引擎    -->   本地库接口 --> 本地方法库
     * <p>
     * >>>程序计数器<<<
     * 线程私有的内存
     * 是当前线程所执行的字节码行号指示器
     * 如果执行的本地方法那么计数器值则为空
     * 唯一一个在JAVA虚拟机规范中没有OOM的内存区域
     * <p>
     * >>>虚拟机栈<<<
     * 线程私有的
     * 每个方法执行的时候，JVM会同步生成一个栈帧用于存储局部变量表、操作数栈、动态连接、方法出口等
     * 每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程
     * 局部变量表里存了编译期可知的java虚拟机基本数据类型，对象引用
     * 如果请求的栈深度超过了虚拟机允许的深度，抛出StackOverflowError异常，线程如果在申请栈空间的时候失败，则会抛出OOM异常
     * <p>
     * >>>本地方法栈<<<
     * 线程私有
     * 跟虚拟机栈类似
     * Hot-Spot虚拟机将这两个栈合二为一
     * <p>
     * >>>堆<<<
     * 所有线程共享的内存区域，几乎所有的对象实例都在这里分配内存（逃逸分析技术的发展，栈上分配和标量替换.....）
     * 当前主流的Java虚拟机都是按照可扩展堆来实现的(通过参数-Xmx和-Xms设定)
     * 一开始的分代思想是新生区（包含一个Eden和两个Survivor）和老年代
     * <p>
     * >>>方法区<<<
     * 线程共享的内存区域
     * 储存已被虚拟机加载的类型信息、常量、静态变量.....
     */
    public static void main(String[] args) {

//        SoftReference<String> str = new SoftReference<>("cola");
//        WeakReference<String> weak = new WeakReference<>("cola");
        int[] numbers = new int[]{2, 0, 2, 1, 1, 0};
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
