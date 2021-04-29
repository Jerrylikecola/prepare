package com.jerrylikecola.prepare.sjms;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/2 11:40
 * @description
 */
public class Decorator {
    interface PhoneCard{
        void howMuch();
    }

    class YiDong implements PhoneCard{

        @Override
        public void howMuch() {
            System.out.println("50块");
        }
    }

    class Card5G implements PhoneCard{

        private PhoneCard phoneCard;

        public Card5G(PhoneCard phoneCard) {
            this.phoneCard = phoneCard;
        }

        @Override
        public void howMuch() {
            phoneCard.howMuch();
            System.out.println("升5g需要提速");
        }
    }

    class gao5G extends Card5G{

        public gao5G(PhoneCard phoneCard) {
            super(phoneCard);
        }

        @Override
        public void howMuch(){
            super.howMuch();
            System.out.println("花了30块提速");
        }
    }

    @Test
    public void test(){
        PhoneCard phoneCard = new gao5G(new YiDong());
        phoneCard.howMuch();
    }
}
