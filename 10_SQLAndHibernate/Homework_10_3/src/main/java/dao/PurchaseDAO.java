package dao;

import entities.Course;
import entities.Purchase;
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO implements DAO<Purchase, String> {

    @Override
    public void create(Purchase purchase) {

    }

    @Override
    public Purchase read(String key) {
        return null;
    }

    @Override
    public List<Purchase> readAll() {

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Object[]> objects = session.createSQLQuery("select distinct " +
                "pl.course_name as course_name, " +
                "pl.student_name as student_name," +
                "students.id as student_id," +
                "courses.id as course_id " +
                "from purchaselist as pl " +
                "left join students on pl.student_name = students.name " +
                "left join courses on pl.course_name = courses.name").getResultList();
        List<Purchase> purchases = new ArrayList<>();

        objects.forEach(object -> {

            Purchase purchase = new Purchase();
            purchase.setCourseName(object[0].toString());
            purchase.setStudentName(object[1].toString());
            purchase.setStudentId(Integer.parseInt(object[2].toString()));
            purchase.setCourseId(Integer.parseInt(object[3].toString()));

            purchases.add(purchase);

        });

        return purchases;

    }

    @Override
    public void update(Purchase purchase) {

    }

    @Override
    public void delete(Purchase purchase) {

    }

}
