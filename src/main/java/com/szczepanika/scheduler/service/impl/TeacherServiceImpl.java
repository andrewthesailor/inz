package com.szczepanika.scheduler.service.impl;

import com.szczepanika.scheduler.dao.TeacherDao;
import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class TeacherServiceImpl implements TeacherService {

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
