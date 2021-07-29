package com.szczepanika.scheduler.dao.impl;

import com.szczepanika.scheduler.dao.TimetableDao;
import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.model.parameters.Timeslot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TimetableDaoImpl implements TimetableDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Timeslot> getTimeslots() {
        String query = "SELECT data FROM Timeslot data WHERE data.active = true";
        TypedQuery<Timeslot> resultQuery = entityManager.createQuery(query, Timeslot.class);
        try {
            return resultQuery.getResultList();
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
