package com.szczepanika.scheduler.dao;

import com.szczepanika.scheduler.model.parameters.Timeslot;

import java.util.List;

public interface TimetableDao {
    List<Timeslot>getTimeslots();

}
