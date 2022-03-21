package com.yanliang.algo.interview;

/**
 * 写出如下对象初始化的打印顺序 // 1a2b2b
 *
 * @author yanliang
 */
class A {
    static {
        System.out.print("1");
    }

    public A() {
        System.out.print("2");
    }
}

class B extends A {
    static {
        System.out.print("a");
    }

    public B() {
        System.out.print("b");
    }
}

public class Print {
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }
}
