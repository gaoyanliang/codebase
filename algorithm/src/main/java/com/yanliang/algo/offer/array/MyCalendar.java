package com.yanliang.algo.offer.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanliang
 */
public class MyCalendar {
    List<Node> list;

    public MyCalendar() {
        this.list = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (Node node: list) {
            if (!(start >= node.end || end <= node.start)) return false;
        }
        list.add(new Node(start, end));
        return true;
    }

    class Node {
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
