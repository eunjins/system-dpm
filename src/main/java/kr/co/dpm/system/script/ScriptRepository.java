package kr.co.dpm.system.script;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScriptRepository {

    public List<Script> selectAll(Map<String, Object> condition);

    public Script select(Script script);

    public void insert(Script script);
}
