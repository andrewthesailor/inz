package com.szczepanika.scheduler.controller;

import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import com.szczepanika.scheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    TimetableService timetableService;

    @RequestMapping("/user")
    public String userPage(Model model, @AuthenticationPrincipal Teacher teacher) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("teacher", teacher);
        model.addAttribute("timeslots", timetableService.getTimeslots());
        return "userPage";
    }

    @RequestMapping(value = "/setTimeslots", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("employee") List<Timeslot> timeslots,
            BindingResult result, ModelMap model,
            @AuthenticationPrincipal Teacher teacher) {
        if (result.hasErrors()) {
            return "error";
        }
        teacher.setAvailableTimeslots(timeslots);
        return "userPage";
    }


}
