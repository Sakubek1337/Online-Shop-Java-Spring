package com.example.demo.Controllers;

import com.example.demo.Models.Entities.User;
import com.example.demo.Models.Order;
import com.example.demo.Service.DataBaseManager;
import com.example.demo.Service.Delivery;
import com.example.demo.Service.RegistrationCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    DataBaseManager dbm;

    @Autowired
    Delivery delivery;

    RegistrationCondition rc = new RegistrationCondition();

    @GetMapping("/")
    public String simple(){
        return "redirect:main";
    }

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("types", dbm.getTypes());
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView order(){
        ModelAndView mav = new ModelAndView("order");
        Order order = new Order();
        order.setSent(false);
        order.setType("life");
        mav.addObject("last_order", order);
        mav.addObject("new_order", order);
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        User user = new User();
        dbm.addAll();
        dbm.addTypeInfo();
        mav.addObject("register", rc);
        mav.addObject("new_user", user);
        return mav;
    }

    @PostMapping("/sendOrder")
    public ModelAndView sendOrder(@ModelAttribute Order order){
        ModelAndView mav = new ModelAndView("order");
        delivery.sendOrder(order.getEmail(), dbm.getQuote(order.getType()));
        order.setSent(true);
        Order order1 = new Order();
        order1.setSent(false);
        order1.setType("life");
        mav.addObject("last_order", order);
        mav.addObject("new_order", order1);
        return mav;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpServletRequest request){
        user.setRole("ROLE_USER");
        if(dbm.checkUser(user.getUsername())){
            rc.setInUse(true);
        }else{
            dbm.addUser(user);
            rc.setInUse(false);
            try {
                request.login(user.getUsername(), user.getPassword());
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return "redirect:main";
    }
}
