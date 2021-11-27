package kr.co.dpm.system.script;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttachService {
    public List<Attach> getAttaches(Attach attach);

    public Attach getAttach(Attach attach);


    public void registerAttach(Attach attach);
}
