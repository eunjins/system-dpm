package kr.co.dpm.system.access;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public User select() throws Exception;
}
