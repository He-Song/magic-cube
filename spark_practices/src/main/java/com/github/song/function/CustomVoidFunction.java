package com.github.song.function;

import org.apache.spark.api.java.function.VoidFunction;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/23 17:40
 */
public class CustomVoidFunction implements VoidFunction {

    @Override public void call(Object o) throws Exception {
        System.out.println(o);
    }
}
