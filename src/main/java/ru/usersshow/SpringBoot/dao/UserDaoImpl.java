package ru.usersshow.SpringBoot.dao;


import org.springframework.stereotype.Repository;
import ru.usersshow.SpringBoot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> getListUser() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User readUser(long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not find!!!");
        }
    }

    @Override
    public void deleteUser(long id) {
        User user = readUser(id);
        if (user == null) {
            throw new EntityNotFoundException("User not find!!!");
        }
        entityManager.remove(user);
        entityManager.flush();

    }
}
