package kr.co.dpm.system.measure;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeasureRepository {
    public void insert(Measure measure);
}
