package com.mbds.nfc.web.controllers;

import com.mbds.nfc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test/")
public class HomeController extends BaseController{

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/home/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        model.addAttribute("user", userService.findUserById(1).getFirstName());
        return "welcome";
    }
}
