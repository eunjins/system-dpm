package kr.co.dpm.system.common;

import org.springframework.stereotype.Component;

@Component
public class StatusCode {
    public static final int OK = 200;
    public static final int NOT_MODIFIED = 304;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
}
