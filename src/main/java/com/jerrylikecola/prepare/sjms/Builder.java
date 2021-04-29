package com.jerrylikecola.prepare.sjms;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/3/31 16:39
 * @description
 */
public class Builder {

    @Data
    class People{
        private String money;
        private String house;
    }

    abstract class MakePeopleLikePeople{

        People people = new People();

        public abstract void gaoQian();

        public abstract void gaoFangZi();

        public People getPeople(){
            return people;
        }
    }

    class XuLiYa extends MakePeopleLikePeople{

        @Override
        public void gaoQian() {
            people.setMoney("10元");
        }

        @Override
        public void gaoFangZi() {
            people.setHouse("叙利亚豪华大床房单间");
        }
    }

    class Jerry{
        private MakePeopleLikePeople makePeopleLikePeople;

        public Jerry(MakePeopleLikePeople makePeopleLikePeople) {
            this.makePeopleLikePeople = makePeopleLikePeople;
        }

        public People makePeople(){
            makePeopleLikePeople.gaoQian();
            makePeopleLikePeople.gaoFangZi();
            return makePeopleLikePeople.getPeople();
        }
    }

    @Test
    public void test(){
        MakePeopleLikePeople makePeopleLikePeople = new XuLiYa();
        Jerry jerry = new Jerry(makePeopleLikePeople);
        System.out.println(jerry.makePeople().getHouse());
        System.out.println(jerry.makePeople().getMoney());
    }
}
