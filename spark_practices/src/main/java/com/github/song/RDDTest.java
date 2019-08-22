package com.github.song;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/20 14:56
 */
public class RDDTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("countWords").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        Long time = System.currentTimeMillis();

        countWords(sc);
        //countWordsLambda(sc);

        System.out.println(String.format("it's spend time: %dms", System.currentTimeMillis() - time));

        sc.close();
    }

    /**
     * 统计bible中字母出现的次数
     * @param sc
     */
    public static void countWords(JavaSparkContext sc) {

        JavaRDD<String> lines = sc.textFile("D:\\Cache\\bibletxt.txt");
        JavaRDD<String> line = lines.flatMap(s -> Arrays.asList(s.split("[*]?")).iterator());

        JavaPairRDD<String, Integer> words = line.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairRDD<Integer, String> wordCount = words.reduceByKey((v1, v2) -> (v1 + v2))
                .mapToPair(entry -> new Tuple2<>(entry._2, entry._1)).sortByKey(false);

        wordCount.foreach(w -> {
            System.out.println(String.format("words: %s, ---> %d", w._2, w._1));
        });

    }

    public static void countWordsLambda(JavaSparkContext sc) {

        JavaPairRDD<Integer, String> wordCount = sc.textFile("D:\\Cache\\test.txt")
                .flatMap(s -> Arrays.asList(s.split("[*]?")).iterator()).mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((v1, v2) -> (v1 + v2)).mapToPair(entry -> new Tuple2<>(entry._2, entry._1))
                .sortByKey(false);

        wordCount.foreach(w -> {
            System.out.println(String.format("words: %s, ---> %d", w._2, w._1));
        });

    }

}
