package com.test.eventbusdemo;

/**
 * Created by zhanggang on 2017/9/27.
 */

public class Bean1 {
    public String name;
    public String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Bean1(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
