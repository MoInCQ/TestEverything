package com.mo.testeverything.SingleInstance.Bean;

/**
 * Created by work on 2018/5/28.
 */

public class StaticSingleInstance {

    private static StaticSingleInstance staticSingleInstance;

    private StaticSingleInstance() {

    }

    public static StaticSingleInstance getStaticSingleInstance() {
        return StaticSingleInstanceHolder.staticSingleInstance;
    }

    private static class StaticSingleInstanceHolder {
        private static StaticSingleInstance staticSingleInstance = new StaticSingleInstance();
    }

}
