import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import song.Application;
import song.engine.Checkes;
import song.engine.CheckesService;
import song.engine.TempletEngineServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JSEngineTest {

    @Autowired
    private TempletEngineServiceImpl engineImpl;

    @Autowired
    private CheckesService checkesService;

    public void checkFomula() {

        List<Checkes> checkes = checkesService.findAll();

    }

    @Test
    public void test() throws FileNotFoundException, ScriptException {

        String jsStr = "!(((b$S6$.substr(6,4)==123) && (b$S6$.substr(10,2)==23232)) || b$S6$=='111111111111111111' || b$S6$=='000000000000000000')";

        Map<String, Object> vars = new HashMap<>();

        Long t1 = System.currentTimeMillis();
        try {
            for (int i = 0; i < 10000; ++i) {
                vars.put("b$S6$", "5113211993052765" + i);
                engineImpl.eval(jsStr, vars);
                // System.out.println("jsstr : " + jsStr + " result : " + engineImpl.eval(jsStr, vars));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Long t2 = System.currentTimeMillis();
        System.err.println(String.format("it spend %d ms excute 1000 times js script", t2 - t1));
        for (int i = 0; i < 10000; ++i) {
            String b$S6$ = "5113211993052765" + i;
            boolean b = !(((b$S6$.substring(6, 10).equals("123") && (b$S6$.substring(10, 12).equals("23232")))
                    || b$S6$.equals("111111111111111111") || b$S6$.equals("000000000000000000")));
            System.out.println("jsstr : " + jsStr + " result : " + b);
        }

        Long t3 = System.currentTimeMillis();
        System.err.println(String.format("it spend %d ms excute 1000 times js script", t2 - t1));
        System.err.println(String.format("it spend %d ms excute 1000 times java script", t3 - t2));
        // String jsStr = "(1+1) > 3";
        /*String jsStr = "var b$S6$ = '111111111111111111'; "
                + "!(((b$S6$.substr(6,4)==32) && (b$S6$.substr(10,2)==232)) || b$S6$=='111111111111111111' || b$S6$=='000000000000000000')";
        try {
        
            System.out.println("jsstr : " + jsStr + " result : " + engineImpl.eval(jsStr));
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        String jsSt1r = "b$S6$";
        try {
            System.out.println(String.format("b$S6$'s value is %s", engineImpl.eval(jsSt1r).toString()));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String jsStr = "b$S6$";
        try {
            System.out.println(String.format("b$S6$'s value is %s", engineImpl.eval(jsStr).toString()));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void addMockData() throws IOException {
        File file = new File("C:\\Users\\76797\\Desktop\\json.txt");
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuffer buffer = new StringBuffer();
        String s;
        while ((s = bf.readLine()) != null) {
            buffer.append(s);
        }
        String json = buffer.toString();
        JSONArray fJson = JSONArray.parseArray(json);
        for (int i = 0; i < fJson.size(); ++i) {
            JSONObject bean = (JSONObject) fJson.get(i);
            Checkes checkes = new Checkes();
            checkes.setCode(bean.getString("code"));
            checkes.setFormula(bean.getString("formula"));
            checkes.setErrInfo(bean.getString("message"));
            checkes.setStatus(1);
            checkes.setTier(1);
            checkes.setType(1);
            checkesService.save(checkes);
        }
        bf.close();

    }

}
