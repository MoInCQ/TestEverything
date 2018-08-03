package com.mo.testeverything.SingleInstance.Bean;

/**
 * Created by work on 2018/5/28.
 */

public class HungrySingleInstance {

    private static HungrySingleInstance hungrySingleInstance = new HungrySingleInstance();

    private HungrySingleInstance() {

    }

    public static HungrySingleInstance getHungrySingleInstance() {
        return hungrySingleInstance;
    }

}
