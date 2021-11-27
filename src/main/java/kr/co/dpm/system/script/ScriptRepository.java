package kr.co.dpm.system.script;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ScriptRepository {

    public List<Script> selectAll(Script script);

    public Script select(Script script);

    public int insert(Script script);
}
