package com.szczepanika.scheduler.service;

import com.szczepanika.scheduler.model.parameters.Timeslot;

import java.util.List;

public interface TimetableService {

    List<Timeslot>getTimeslots();

}
