package com.szczepanika.scheduler.controller;

import com.szczepanika.scheduler.model.parameters.Timeslot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    @GetMapping({"/", "/user"})
    public String user(Model model){
        return "index";
    }

    @GetMapping("/getTimeslots")
    public List<Timeslot> getTimeslots(){
        return null;
    }

    @PostMapping("/setTimeslots")
    public ResponseEntity setTimeslots(@RequestBody List<Timeslot> timeslots){

        return null;
    }

}
