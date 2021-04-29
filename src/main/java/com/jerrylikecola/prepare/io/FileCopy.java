package com.jerrylikecola.prepare.io;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * @author xiaxiang
 * @date 2021/4/13 10:27
 * @description
 */
public class FileCopy {

    @Test
    public void copyFile() throws IOException {
        File file1 = new File("/Users/xuyinjie/Downloads/num1.text");
        File file2 = new File("/Users/xuyinjie/Downloads/num2.text");
//        copy1(file1,file2);
        // 第二种
//        Files.copy(file1.toPath(),file2.toPath());
        int a = Stream.of(2,1,4,5,3).max(Integer::compare).get();
        System.out.println(a);
    }

    public void copy1(File file1, File file2) throws IOException {
        FileChannel fileChannel1 = null;
        FileChannel fileChannel2 = null;
        try {
            fileChannel1 = new FileInputStream(file1).getChannel();
            fileChannel2 = new FileOutputStream(file2).getChannel();
            fileChannel2.transferFrom(fileChannel1, 0, fileChannel1.size());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            fileChannel1.close();
            fileChannel2.close();
        }
    }

    public static void main(String[] args) {
        while (true){
            new Thread().start();
        }
    }
}
