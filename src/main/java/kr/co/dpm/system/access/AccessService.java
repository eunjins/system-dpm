package kr.co.dpm.system.access;

import org.springframework.stereotype.Service;

@Service
public interface AccessService {
    //관리자 정보 조회
    public User getManager();
}
