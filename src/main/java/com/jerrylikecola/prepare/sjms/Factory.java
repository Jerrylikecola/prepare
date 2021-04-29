package com.jerrylikecola.prepare.sjms;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/3/31 15:54
 * @description
 */
public class Factory {
    interface Fruit {
        void print();
    }

    interface Shop {
        Fruit showFruit(String name);
    }

    class Apple implements Fruit {
        @Override
        public void print() {
            System.out.println("你要苹果不要");
        }
    }

    class Pear implements Fruit {
        @Override
        public void print() {
            System.out.println("你要梨头不要");
        }
    }

    class JerryShop implements Shop {
        @Override
        public Fruit showFruit(String name) {
            if ("apple".equals(name)) {
                return new Apple();
            }
            if ("pear".equals(name)) {
                return new Pear();
            }
            return null;
        }
    }

    @Test
    public void test() {
        Shop shop = new JerryShop();
        shop.showFruit("apple").print();
        shop.showFruit("pear").print();
    }
}
