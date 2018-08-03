package com.mo.testeverything.SingleInstance.Bean;

/**
 * Created by work on 2018/5/28.
 */

public class LazySingleInstance {

    private static LazySingleInstance lazySingleInstance;

    private LazySingleInstance() {

    }

    public static LazySingleInstance getLazySingleInstance(){
        if (lazySingleInstance == null) {
            lazySingleInstance = new LazySingleInstance();
        }
        return lazySingleInstance;
    }

}
