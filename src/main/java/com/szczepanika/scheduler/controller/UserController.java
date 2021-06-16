package com.szczepanika.scheduler.controller;

import com.szczepanika.scheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    TimetableService timetableService;

    @RequestMapping("/user")
    public String userPage(Model model){

        model.addAttribute();
        return "userPage";
    }



}
