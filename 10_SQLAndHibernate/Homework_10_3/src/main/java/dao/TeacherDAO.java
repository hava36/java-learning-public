package dao;

import entities.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeacherDAO implements DAO<Teacher, Integer> {
    @Override
    public void create(Teacher teacher) {

    }

    @Override
    public Teacher read(Integer id) {
        return null;
    }

    @Override
    public List<Teacher> readAll() {

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);
        query.select(root);
        Query q=session.createQuery(query);
        List<Teacher> teachers = q.getResultList();

        return teachers;

    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public void delete(Teacher teacher) {

    }
}
