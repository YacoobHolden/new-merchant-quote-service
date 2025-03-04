package nz.co.smartpay.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize("hasAnyRole('USER')")
@RequestMapping(value="/home")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadHomePage(Model model) {
        return "index";
    }

}
