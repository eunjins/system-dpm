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
    UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("access/login");
    }

    @PostMapping("/login")
    public ModelAndView login(User user, HttpSession httpSession) {
        ModelAndView mav = null;
        User userInfo = null;

        try {
            userInfo = userRepository.select();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user.getId().equals(userInfo.getId())
                && user.getPassword().equals(userInfo.getPassword())) {
            httpSession.setAttribute("log", user.getId());

            mav = new ModelAndView(new RedirectView("/devices"));
        } else {
            mav = new ModelAndView("access/login");
            mav.addObject("missMatch",
                    "로그인 정보가 일치하지 않습니다");
        }

        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();

        return new ModelAndView(new RedirectView("/login"));
    }
}