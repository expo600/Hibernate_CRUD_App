package com.ryzhov_andrey.crud.repository.impl;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    private Transaction transaction;

    @Override
    public Specialty getById(Long id) {
        Specialty specialty = new Specialty();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            specialty = session.get(Specialty.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public Specialty create(Specialty specialty) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(specialty);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(specialty);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return specialty;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("UPDATE Specialty SET status =: status WHERE id =: id")
                    .setParameter("status", Status.DELETED)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialtyList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery sqlQuery = session
                    .createSQLQuery("SELECT * FROM specialties WHERE status_name = 'ACTIVE' ORDER BY id ;");
            sqlQuery.addEntity(Specialty.class);
            specialtyList = sqlQuery.list();
        } catch (HibernateException e) {
        }
        return specialtyList;
    }
}
