package kr.co.dpm.system.status;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
    public static final String OK_MSG= "정상 요청";
    public static final String NOT_MODIFIED_MSG= "변경된 사실 없음";
    public static final String BAD_REQUEST_MSG= "잘못된 요청";
    public static final String NOT_FOUND_MSG= "요청 페이지 찾을 수 없음";
    public static final String INTERNAL_SERVER_ERROR_MSG= "내부 서버 오류";
}
