package kr.co.dpm.system.script;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachRepository {
    public Attach select(Attach attach);

    public void insert(Attach attach);
}
