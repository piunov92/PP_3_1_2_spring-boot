package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);

    void updateUser(User user);
}
