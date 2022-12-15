package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public User getUserByCarSeriesAndModel(String model, int series) {
      try (Session session = sessionFactory.openSession()) {
         String HQL = "from User where car = (from Car where model = :model and series = :series)";
         return session.createQuery(HQL, User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .getSingleResult();
      } catch (HibernateException e) {
         e.printStackTrace();
      }
      return null;
   }
}
