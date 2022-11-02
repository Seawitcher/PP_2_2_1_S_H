package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Transactional
   public List<User> getUserWhoCarAccordingModelAndSeries(String model, int series){
      TypedQuery<User> query = null;
      try(Session session = sessionFactory.getCurrentSession()) {

        String HQL="FROM User user WHERE user.car.model =:model AND user.car.series =:series";
         query = session.createQuery(HQL, User.class).setParameter("model", model)
                .setParameter("series", series);


      } catch (HibernateException e) {
         e.printStackTrace();
      }
      return query.getResultList();
   }


}
