package com.szczepanika.scheduler.service.impl;

import com.szczepanika.scheduler.dao.TeacherDao;
import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TeacherDao teacherDao;


    @Override
    public Teacher loadUserByUsername(String s) throws UsernameNotFoundException {
        Teacher teacher = teacherDao.getTeacherByEmail(s);
        if (teacher == null) {
            throw new UsernameNotFoundException("Wrong email or password");
        }
        return teacher;
    }
}
