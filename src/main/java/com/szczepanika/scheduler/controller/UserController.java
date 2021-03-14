package com.szczepanika.scheduler.controller;

import com.szczepanika.scheduler.model.json.User;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return true;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
    @PostMapping("/setTimeslots")
    public ResponseEntity setTimeslots(@RequestBody List<Timeslot> timeslots){

        return null;
    }

}
