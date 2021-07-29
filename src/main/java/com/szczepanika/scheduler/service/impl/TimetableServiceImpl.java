package com.szczepanika.scheduler.service.impl;

import com.szczepanika.scheduler.dao.TimetableDao;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import com.szczepanika.scheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    TimetableDao timetableDao;

    @Override
    public List<Timeslot> getTimeslots() {
        return timetableDao.getTimeslots();
    }
}
