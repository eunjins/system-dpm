package kr.co.dpm.system.script;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ScriptService {
    public List<Script> getScripts(Map<String, Object> condition);

    public Script getScript(Script script);

    public void registerScript(Script script);
}
