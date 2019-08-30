import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/28 11:05
 */
public class JsonTest {

    private String str =
            "{\"code\":\"e017\",\"formula\":\"!a$DCY$ || !isChinese(a$DCY$) || a$DCY$.length<2\",\"message\""
                    + ":\"【DCY】调查员（签字）不能为空,至少两个汉字\",\"state\":\"启用\",\"stype\":\"必要审核\",\"sclass\":\"其他\",\"tier\""
                    + ":\"1\",\"range\":\"\",\"made\":\"\",\"type\":\"\",\"level\":1}";

    @Test public void test() {
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        Double i = 0.01 * 150 * 100000000 / 60 / 60 /24;
        System.out.println(i);

    }
}
