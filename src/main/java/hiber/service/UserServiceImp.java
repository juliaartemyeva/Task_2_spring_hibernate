package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean ifUserExists(String firstName, String lastName, String email) {
        return userDao.ifUserExists(firstName, lastName, email);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByNamesAndEmail(String firstName, String lastName, String email) {
        return userDao.getUserByNamesAndEmail(firstName, lastName, email);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean ifUserExistsByCar(String name, int series) {
        return userDao.ifUserExistsByCar(name, series);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByCar(String name, int series) {
        return userDao.getUserByCar(name, series);
    }

    @Transactional
    @Override
    public void deleteUser(String firstName, String lastName, String email) {
        userDao.deleteUser(firstName, lastName, email);
    }

    @Transactional
    @Override
    public void deleteCarById(Long id) {

    }
}
