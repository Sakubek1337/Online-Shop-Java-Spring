package com.example.demo.Controllers;

import com.example.demo.Entities.Quote;
import com.example.demo.Service.DataBaseManager;
import com.example.demo.Service.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    DataBaseManager dbm;

    @Autowired
    Delivery delivery;

    @GetMapping("/")
    public String simple(){
        return "redirect:main";
    }

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("main");
        dbm.addAll();
        mav.addObject("quotes_types", dbm.getTypes());
        return mav;
    }

    @GetMapping("/auth")
    public ModelAndView auth(){
        ModelAndView mav = new ModelAndView("authorization");
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView order(){
        ModelAndView mav = new ModelAndView("order");
        delivery.sendOrder("salamatbek10@gmail.com", dbm.getQuote("life"));
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @PostMapping("/sendOrder")
    public String send(@ModelAttribute Quote quote, String email){
        delivery.sendOrder(email, dbm.getQuote(quote.getType()));
        return "redirect:main";
    }
}
