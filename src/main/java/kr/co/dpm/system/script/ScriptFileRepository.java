package kr.co.dpm.system.script;

public interface ScriptFileRepository {
    // 스크립트 배포 ( -> 에이전트 )
    public boolean distribute(Script script);
}
