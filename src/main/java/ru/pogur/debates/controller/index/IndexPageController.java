package ru.pogur.debates.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.pogur.debates.model.SecUser;
import ru.pogur.debates.service.user.UserService;

import java.util.Collection;

/**
 * Created by dima-pc on 12.02.2016.
 */
@Controller
@RequestMapping("/")
public class IndexPageController {
     
    private static final String ADMIN_AUTHORITY_NAME = "ROLE_ADMIN";

    @Autowired
    private UserService userService;

    @RequestMapping("/wellcome")
    public String wellcome(@AuthenticationPrincipal UserDetails userDetails){
        return "wellcome_page";
    }

    //may need to use ServletContext context
    @RequestMapping("/index")
    public ModelAndView index(@AuthenticationPrincipal UserDetails userDetails){
        ModelAndView modelAndView = new ModelAndView("index/index");//, new FreeMarkerView());

        String viewName;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        if (isAdmin(authorities)) {
            viewName = "admin/index";
        } else {
            viewName = "user/index";
        }

        SecUser user = userService.getUserByUsername(userDetails.getUsername());
        modelAndView.setViewName(viewName);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    private boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(ADMIN_AUTHORITY_NAME)) {
                return true;
            }
        }
        return false;
    }
}