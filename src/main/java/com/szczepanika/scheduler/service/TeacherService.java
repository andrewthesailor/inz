package com.szczepanika.scheduler.service;


import com.szczepanika.scheduler.model.parameters.Teacher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface TeacherService{

    Teacher loadUserByUsername(String s) throws UsernameNotFoundException;
}
