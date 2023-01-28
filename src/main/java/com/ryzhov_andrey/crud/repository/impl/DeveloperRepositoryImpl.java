package com.ryzhov_andrey.crud.repository.impl;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {

    private Transaction transaction;

    @Override
    public Developer getById(Long id) {
        Developer developer = new Developer();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            developer = session.get(Developer.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer create(Developer developer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(developer);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Developer resultDeveloper = (Developer) session.merge(developer);
            transaction.commit();
            return resultDeveloper;
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("UPDATE Developer SET status =: status WHERE id =: id")
                    .setParameter("status", Status.DELETED)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SQLQuery sqlQuery = session
                    .createSQLQuery("SELECT * FROM developers WHERE status_name = 'ACTIVE' ORDER BY id ;");
            sqlQuery.addEntity(Developer.class);
            developerList = sqlQuery.list();
        } catch (HibernateException e) {
        }
        return developerList;
    }
}
