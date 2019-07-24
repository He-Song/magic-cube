package song.engine;

import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

@Service
public class TempletEngineServiceImpl implements TempletEngineService {

    private ScriptEngine jsEngine;

    public TempletEngineServiceImpl() {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByName("JavaScript");
    }

    @Override
    public Object eval(String script) throws ScriptException {
        if (script == null || "".equals(script)) {
            return null;
        }
        return jsEngine.eval(script);
    }

    @Override
    public Object eval(String script, Map<String, Object> vars) throws ScriptException {
        if (script == null || "".equals(script)) {
            return null;
        }
        if (vars != null && !vars.isEmpty()) {
            for (Map.Entry<String, Object> entry : vars.entrySet()) {
                jsEngine.put(entry.getKey(), entry.getValue());
            }
        }
        return eval(script);
    }
}
