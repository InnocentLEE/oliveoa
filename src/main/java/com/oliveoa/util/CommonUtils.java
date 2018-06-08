package com.oliveoa.util;

import java.util.UUID;

/**
 * Created by Lee on 2018/5/18.
 */
public class CommonUtils {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
