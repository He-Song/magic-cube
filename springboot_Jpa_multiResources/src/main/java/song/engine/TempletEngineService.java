package song.engine;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.script.ScriptException;

public interface TempletEngineService {

    public Object eval(String script) throws ScriptException, FileNotFoundException;

    public Object eval(String script, Map<String, Object> vars) throws ScriptException, FileNotFoundException;

}