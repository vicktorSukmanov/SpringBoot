package ru.usersshow.SpringBoot.dao;


import ru.usersshow.SpringBoot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getListUser();
    void createUser(User user);
    void updateUser(User user);
    User readUser(long id);
    void deleteUser(long id);

}
