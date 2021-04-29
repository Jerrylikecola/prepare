package com.jerrylikecola.prepare.java;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author xiaxiang
 * @date 2021/3/10 14:34
 * @description
 */
public class OptionalTest {

    @Test
    public void test(){
        Optional<String> str = Optional.of("tom");
        Optional<String> strNull = Optional.empty();
        System.out.println(str.isPresent());// true
        System.out.println(strNull.isPresent());// false
        String changeElse = str.orElse("jerry");
        String changeElseGet = str.orElseGet(()->"jerry");
        System.out.println(changeElse);
        System.out.println(changeElseGet);

    }
}
