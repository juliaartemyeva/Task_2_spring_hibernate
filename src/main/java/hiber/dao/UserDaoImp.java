package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public boolean ifUserExists(String firstName, String lastName, String email) {
      return nonNull(getIdByNamesAndEmail(firstName, lastName, email));
   }

   @Override
   public User getUserByNamesAndEmail(String firstName, String lastName, String email) {
      Long id = getIdByNamesAndEmail(firstName, lastName, email);
      Car car = sessionFactory.getCurrentSession().createQuery("from Car where id = '"+id+"'", Car.class).getSingleResult();
      User user = new User(id, firstName, lastName, email, car);
      return user;
   }

   @Override
   public boolean ifUserExistsByCar(String name, int series) {
      return nonNull(getIdByCar(name, series));
   }

   @Override
   public User getUserByCar(String name, int series) {
      Long id = getIdByCar(name, series);
      return sessionFactory.getCurrentSession().createQuery("from User where id = '"+id+"'", User.class).getSingleResult();
   }

   @Override
   public void deleteUser(String firstName, String lastName, String email) {
      if(ifUserExists(firstName, lastName,email)) {
         User user = getUserByNamesAndEmail(firstName, lastName, email);
         sessionFactory.getCurrentSession().delete(user);
         deleteCarById(user.getId());

      }
   }

   @Override
   public void deleteCarById(Long id) {
      sessionFactory.getCurrentSession().createQuery("delete from Car where id = '"+id+"'").executeUpdate();
   }

   public Long getIdByNamesAndEmail(String firstName, String lastName, String email) {
      return (Long) sessionFactory.getCurrentSession().createQuery("select id from User where " +
              "firstName = '"+firstName+"'and lastName = '"+lastName+"' and email = '"+email+"'").getSingleResult();
   }

   public Long getIdByCar(String name, int series) {
      return (Long)sessionFactory.getCurrentSession().createQuery("select id from Car where " +
              "name = '"+name+"'and series = '"+series+"'").getSingleResult();
   }
}
