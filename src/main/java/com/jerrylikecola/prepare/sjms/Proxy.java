package com.jerrylikecola.prepare.sjms;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/2 10:40
 * @description
 */
public class Proxy {

    interface PhoneCard{
        void howMuch();
    }

    class YiDong implements PhoneCard{

        @Override
        public void howMuch() {
            System.out.println("收您30元");
        }
    }

    class YiDongSchoolProxy implements PhoneCard{

        PhoneCard phoneCard = new YiDong();

        @Override
        public void howMuch() {
            System.out.println("给我10块钱的手续费");
            phoneCard.howMuch();
        }
    }

    @Test
    public void test(){
        PhoneCard phoneCard = new YiDongSchoolProxy();
        phoneCard.howMuch();
    }
}
