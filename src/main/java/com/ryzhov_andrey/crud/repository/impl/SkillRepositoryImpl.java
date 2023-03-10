package com.ryzhov_andrey.crud.repository.impl;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {

    private Transaction transaction;

    public SkillRepositoryImpl() {
    }

    @Override
    public Skill getById(Long id) {
        Skill skill = new Skill();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            skill = session.get(Skill.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill create(Skill skill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(skill);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(skill);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("UPDATE Skill SET status =: status WHERE id =: id")
                    .setParameter("status", Status.DELETED)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM skills WHERE status_name = 'ACTIVE' ORDER BY id ;");
            sqlQuery.addEntity(Skill.class);
            skillList = sqlQuery.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return skillList;
    }
}
