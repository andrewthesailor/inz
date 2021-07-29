package com.szczepanika.scheduler.controller;

import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import com.szczepanika.scheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    TimetableService timetableService;

    @RequestMapping("/admin")
    public String adminPage(Model model){

        model.addAttribute("currentTimeslots", timetableService.getTimeslots());

        return "adminPage";
    }

    @RequestMapping(value = "/createNewSlots", method = RequestMethod.POST)
    public String submitNewExams(
            BindingResult result, ModelMap model,
            Date startDate, Date endDate) {
        if (result.hasErrors()) {
            return "error";
        }

        return "adminPage";
    }

    @RequestMapping(value = "/setActiveTimeslots", method = RequestMethod.POST)
    public String submitActive(
            @ModelAttribute("employee") List<Timeslot> timeslots,
            BindingResult result, ModelMap model,
            @AuthenticationPrincipal Teacher teacher) {
        if (result.hasErrors()) {
            return "error";
        }
        teacher.setAvailableTimeslots(timeslots);
        return "adminPage";
    }

}
