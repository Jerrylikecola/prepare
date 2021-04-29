package com.jerrylikecola.prepare.sjms;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/2 11:21
 * @description
 */
public class Adapter {
    interface Water {
        void print();
    }

    class Cola implements Water {

        @Override
        public void print() {
            System.out.println("咕噜咕噜发出了快乐的声音");
        }
    }

    class Milk implements Water {

        @Override
        public void print() {
            System.out.println("有点腻");
        }
    }

    class Mouse {
        private Water water;

        public Mouse(Water water) {
            this.water = water;
        }

        public void drink() {
            water.print();
        }
    }

    @Test
    public void test() {
        Mouse mouse = new Mouse(new Cola());
        mouse.drink();
    }
}
