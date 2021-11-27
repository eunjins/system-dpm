package kr.co.dpm.system.script;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScriptService {
    public List<Script> getScripts(Script script);

    public Script getScript(Script script);

    public int registerScript(Script script);
}
