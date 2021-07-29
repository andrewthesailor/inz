package com.szczepanika.scheduler.dao;

import com.szczepanika.scheduler.model.parameters.Teacher;

public interface TeacherDao {

    Teacher getTeacherByEmail(String email);


}
