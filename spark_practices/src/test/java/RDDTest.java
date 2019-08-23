import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/23 18:09
 */
public class RDDTest {
    SparkConf conf;

    JavaSparkContext sc;

    @Test public void filter() {

    }

    @Test public void flatMap() {

        JavaRDD<String> lines = sc.parallelize(Arrays.asList("hello moto", "hello apple"));

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());

        words.foreach(word -> System.out.println(word));
        sc.close();
    }

    @Before public void start() {
        conf = new SparkConf().setAppName("countWords").setMaster("local");
        sc = new JavaSparkContext(conf);
    }

    @After public void end() {
        sc.close();
    }

}
