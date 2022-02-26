package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User get(String carModel, int carSeries) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :carModel and car.series = :carSeries");
        query.setParameter("carModel", carModel);
        query.setParameter("carSeries", carSeries);
        query.setMaxResults(1);
        return (User) query.getResultStream().findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
