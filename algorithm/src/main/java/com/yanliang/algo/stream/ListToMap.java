package com.yanliang.algo.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanliang
 * @since 2022/5/13 09:49
 */
public class ListToMap {
    public static void main(String[] args) {
        System.out.println(" asdf sdfa asdf adf    adf adf  ".replace(" ", ""));

        System.out.println(Boolean.FALSE.equals(null));


//        List<Apple> appleList = new ArrayList<>();//存放apple对象集合
//
//        appleList.addAll(null);
//
//        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
//        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
//        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
//        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);
//
//        appleList.add(apple1);
//        appleList.add(apple12);
//        appleList.add(apple2);
//        appleList.add(apple3);
//
//        //List 以ID分组 Map<Integer,List<Apple>>
//        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
//
//        System.err.println("groupBy:"+groupBy);
//
//        /**
//         * List -> Map
//         * 需要注意的是：
//         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
//         *  apple1,apple12的id都为1。
//         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
//         */
//        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k2));
//
//        System.err.println("appleMap:"+appleMap);
//
//        Map<Integer, Apple> appleMap2 = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a));

    }

}
