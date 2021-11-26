package kr.co.dpm.system.measure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureServiceImpl implements MeasureService {
    @Autowired
    private MeasureRepository measureRepository;

    /* 측정 결과 목록 조회 */
    @Override
    public List<Measure> getMeasures(Measure measure) {
        return measureRepository.selectAll(measure);
    }

    /* 측정 결과 조회 */
    @Override
    public Measure getMeasure(Measure measure) {
        return measureRepository.select(measure);
    }

    /* 측정 결과 등록 */
    @Override
    public void registerMeasure(Measure measure) {
        measureRepository.insert(measure);
    }
}
