package kr.co.dpm.system.script;

import java.util.List;

public interface ScriptService {
    public List<Script> getScripts(Script script);
    public Script getScript(Script script);
    public void registerScript(Script script);
}
