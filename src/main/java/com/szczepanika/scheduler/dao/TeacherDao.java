package com.szczepanika.scheduler.dao;

import com.szczepanika.scheduler.model.parameters.Teacher;

public interface TeacherDao {

    public Teacher getTeacherByEmail(String email);


}
