package com.github.song;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/23 17:30
 */
public class RDDTest {

    public static void main(String[] args) {
        flatMap();
    }

    private static void flatMap() {
        SparkConf conf = new SparkConf().setAppName("countWords").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.parallelize(Arrays.asList("hello moto", "hello apple"));

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());

        /*CustomVoidFunction voidFunction = new CustomVoidFunction();
        words.foreach(voidFunction::call);*/

        words.foreach(word -> System.out.println(word));
        sc.close();
    }
}
