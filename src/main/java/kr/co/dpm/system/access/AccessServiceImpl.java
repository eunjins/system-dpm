package kr.co.dpm.system.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    UserRepository userRepository;

    //관리자 정보 조회
    @Override
    public User getManager() {
        return userRepository.select();
    }
}