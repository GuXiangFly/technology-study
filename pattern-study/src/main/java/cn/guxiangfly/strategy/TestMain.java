package cn.guxiangfly.strategy;

import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {
        Dog[] dogs = {new Dog(1), new Dog(2) ,new Dog(0)};
        Sorter.sort(dogs,new DogComparator());

        System.out.println(Arrays.toString(dogs));
    }
}
