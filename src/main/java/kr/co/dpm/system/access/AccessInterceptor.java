package kr.co.dpm.system.access;

import kr.co.dpm.system.script.ScriptController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(AccessInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            Object authInfo = session.getAttribute("log");
            if (authInfo != null) {
                logger.debug("-------> 세션 존재");

                return true;
            }
        }
        logger.debug("-------> 세션 미존재");
        response.sendRedirect(request.getContextPath() + "/login");

        return false;
    }
}
