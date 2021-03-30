package dao;

import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDAO implements DAO<Student, Integer> {
    @Override
    public void create(Student student) {

    }

    @Override
    public Student read(Integer integer) {
        return null;
    }

    @Override
    public List<Student> readAll() {

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);
        Query q=session.createQuery(query);
        List<Student> students = q.getResultList();

        return students;

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }
}
