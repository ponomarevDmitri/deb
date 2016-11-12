package ru.pogur.debates.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pogur.debates.model.security.SecUser;
import ru.pogur.debates.service.user.UserService;

import java.util.Collection;

/**
 * Created by dima-pc on 12.02.2016.
 */
@Controller
public class IndexPageController {

    @RequestMapping("/wellcome")
    public String wellcome(@AuthenticationPrincipal UserDetails userDetails){
        return "wellcome_page";
    }

    @RequestMapping("/")
    public ModelAndView index(@AuthenticationPrincipal UserDetails userDetails){
        return new ModelAndView("wellcome_page");
    }
}
