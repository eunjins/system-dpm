package kr.co.dpm.system.script;

import java.util.List;

public interface ScriptRepository {
    public List<Script> selectAll(Script script);
    public Script select(Script script);
    public void insert(Script script);
}
