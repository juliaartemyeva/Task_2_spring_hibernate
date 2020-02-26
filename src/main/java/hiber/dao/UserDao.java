package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   List<User> listUsers();

   boolean ifUserExists(String firstName, String lastName, String email);

   User getUserByNamesAndEmail(String firstName, String lastName, String email);

   boolean ifUserExistsByCar(String name, int series);

   User getUserByCar(String name, int series);

   void deleteUser(String firstName, String lastName, String email);

   void deleteCarById(Long id);
}
