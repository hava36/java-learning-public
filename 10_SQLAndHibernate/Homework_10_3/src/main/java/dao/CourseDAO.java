package dao;

import entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseDAO implements DAO<Course, Integer> {
    @Override
    public void create(Course course) {

    }

    @Override
    public Course read(Integer id) {
        return null;
    }

    @Override
    public List<Course> readAll() {

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        Query q=session.createQuery(query);
        List<Course> courses = q.getResultList();

        return courses;

    }

    @Override
    public void update(Course course) {

    }

    @Override
    public void delete(Course course) {

    }
}
