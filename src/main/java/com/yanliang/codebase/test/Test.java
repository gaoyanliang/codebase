package com.yanliang.codebase.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * @author yanliang
 * @date 2021/2/415:18
 */
public class Test {

    public static class ParentA {
        static {
            System.out.println("1");
        }

        public ParentA() {
            System.out.println("2");
        }

    }

    static  class SonB extends ParentA {
        static {
            System.out.println("a");
        }

        public SonB() {
            System.out.println("b");
        }

        public static void main(String[] args) {

            ParentA ab = new SonB();
            ab = new SonB();
        }
    }


}
