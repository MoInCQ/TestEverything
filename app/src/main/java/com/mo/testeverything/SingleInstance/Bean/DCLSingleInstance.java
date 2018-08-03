package com.mo.testeverything.SingleInstance.Bean;

/**
 * Created by work on 2018/5/28.
 */

public class DCLSingleInstance {

    private static DCLSingleInstance dclSingleInstance = null;

    private DCLSingleInstance() {

    }

    public static DCLSingleInstance getDclSingleInstance() {
        if (dclSingleInstance == null) {

            synchronized (DCLSingleInstance.class) {
                if (dclSingleInstance == null) {
                    dclSingleInstance = new DCLSingleInstance();
                }
            }
        }

        return dclSingleInstance;
    }
}
