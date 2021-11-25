package kr.co.dpm.system.script;

import java.util.List;

public interface ScriptRepository {
    // 스크립트 정보 목록 조회
    public List<Script> selectAll(Script script);
    // 스크립트 정보 조회
    public Script select(Script script);
    // 스크립트 정보 등록
    public void insert(Script script);
}
