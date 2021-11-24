package kr.co.dpm.system.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class AccessController {
    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    // 로그인 폼
    @GetMapping("/login")
    public ModelAndView login(HttpSession httpSession) {
        ModelAndView modelAndView = null;
        if (httpSession.getAttribute("log") != null) {
            modelAndView = new ModelAndView(new RedirectView("/logout"));
        } else {
            modelAndView = new ModelAndView("access/login");
        }

        return modelAndView;
    }
    // 로그인
    @PostMapping("/login")
    public ModelAndView login(User user, HttpSession httpSession) {
        ModelAndView modelAndView = null;
        // ID와 Password가 존재한다면 일치하는지 비교
        if (user.getId() != null && user.getPassword() != null) {
            User userInfo = userRepositoryImpl.select();
            // ID와 Password가 일치한다면 세션 추가 후 로그인 완료
            if (user.getId().equals(userInfo.getId())
                    && user.getPassword().equals(userInfo.getPassword())) {
                httpSession.setAttribute("log", user.getId());
                modelAndView = new ModelAndView("access/test");
                // ID와 Password가 일치하지 않는다면? 로그인 정보가 일치하지 않습니다...
            } else {
                modelAndView = new ModelAndView(new RedirectView("/login"));
            }
        // ID와 Password가 존재하지 않는다면? 로그인 정보가 일치하지 않습니다....
        } else {
            //로그인 정보가 일치하지 않습니다. 비동기 처리!!!!
            modelAndView = new ModelAndView(new RedirectView("/login"));
        }

        return modelAndView;
    }
    // 로그아웃
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();

        return new ModelAndView(new RedirectView("/login"));
    }
}
