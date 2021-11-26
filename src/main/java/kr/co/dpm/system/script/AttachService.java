package kr.co.dpm.system.script;

import org.springframework.stereotype.Service;

@Service
public interface AttachService {
    public Attach getAttach(Attach attach);

    public void registerAttach(Attach attach);
}
