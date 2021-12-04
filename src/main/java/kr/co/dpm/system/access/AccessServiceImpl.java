package kr.co.dpm.system.access;

import kr.co.dpm.system.access.User;
import kr.co.dpm.system.access.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getManager() throws Exception {
        return userRepository.select();
    }
}