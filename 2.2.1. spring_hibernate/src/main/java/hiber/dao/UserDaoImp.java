package hiber.dao;

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
   public void getUserWhoCarAccordingModelAndSeries(String model){
      System.out.println("Зашли в метод !!!!");
      try(Session session = sessionFactory.getCurrentSession()) {

        String HQL="FROM User  WHERE u.car.model:=model";
        User user = (User) session.createQuery(HQL, User.class).setParameter("model", "Lada" ).uniqueResult();
         System.out.println(user);
      } catch (HibernateException e) {
         e.printStackTrace();
      }
   }

//


//   private static void getEmployeeAndAdressByAddressId() {
//
//
//
//         String HQL="FROM Address addr LEFT OUTER JOIN FETCH addr.employee WHERE addr.addressId=:addrId";
//         Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).uniqueResult();
//         System.out.println(address);
//         System.out.println(address.getEmployee());
//      } catch (HibernateException e) {
//         e.printStackTrace();
//      }

}
