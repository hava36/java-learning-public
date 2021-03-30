package dao;

import entities.LinkedPurchase;
import entities.Purchase;
import entities.keys.LinkedPurchaseKey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LinkedPurchaseDAO implements DAO<LinkedPurchase, String> {

    @Override
    public void create(LinkedPurchase linkedPurchase) {

    }

    @Override
    public LinkedPurchase read(String s) {
        return null;
    }

    @Override
    public List readAll() {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LinkedPurchase> query = builder.createQuery(LinkedPurchase.class);
        Root<LinkedPurchase> root = query.from(LinkedPurchase.class);
        query.select(root);
        Query q=session.createQuery(query);
        List<LinkedPurchase> purchases = q.getResultList();
        return purchases;
    }

    @Override
    public void update(LinkedPurchase linkedPurchase) {
        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (session.get(LinkedPurchase.class,
                new LinkedPurchaseKey(linkedPurchase.getId().getStudentId(),
                        linkedPurchase.getId().getCourseId())) == null) {
            session.save(linkedPurchase);
        }
        transaction.commit();
    }

    @Override
    public void delete(LinkedPurchase purchase) {

    }

}
