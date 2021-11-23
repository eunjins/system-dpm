package kr.co.dpm.system.access.service;

import kr.co.dpm.system.model.User;

public interface AccessService {
    //관리자 정보 조회
    public User getManager();
}
