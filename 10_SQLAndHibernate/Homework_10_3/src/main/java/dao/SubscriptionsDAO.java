package dao;

import entities.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.ConnectionUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubscriptionsDAO implements DAO<Subscription, String> {

    @Override
    public void create(Subscription subscription) {

    }

    @Override
    public Subscription read(String s) {
        return null;
    }

    @Override
    public List<Subscription> readAll() {

        SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
        Root<Subscription> root = query.from(Subscription.class);
        query.select(root);
        Query q=session.createQuery(query);
        List<Subscription> subscriptions = q.getResultList();

        return subscriptions;

    }

    @Override
    public void update(Subscription subscription) {

    }

    @Override
    public void delete(Subscription subscription) {

    }

}
