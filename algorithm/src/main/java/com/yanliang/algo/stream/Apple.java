package com.yanliang.algo.stream;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yanliang
 * @since 2022/5/13 09:48
 */
@Data
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;
    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}
