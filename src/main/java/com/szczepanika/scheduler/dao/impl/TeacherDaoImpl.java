package com.szczepanika.scheduler.dao.impl;

import com.szczepanika.scheduler.dao.TeacherDao;
import com.szczepanika.scheduler.model.parameters.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Teacher getTeacherByEmail(String email) {
        String query = "SELECT data FROM Teacher data WHERE data.email = :email";
        TypedQuery<Teacher> resultQuery = entityManager.createQuery(query, Teacher.class).setParameter("email", email);
        try {
            return resultQuery.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
