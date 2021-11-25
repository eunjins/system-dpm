package kr.co.dpm.system.access;

public interface UserRepository {
    // 관리자 아이디, 패스워드 조회 (porperties 파일)
    public User select();
}
