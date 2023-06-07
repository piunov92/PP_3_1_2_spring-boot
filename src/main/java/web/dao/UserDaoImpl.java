package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import web.models.User;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    
    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
            entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        User selectUser = getUserById(user.getId());
        selectUser.setName(user.getName());
        selectUser.setSurname(user.getSurname());
        selectUser.setAge(user.getAge());
        entityManager.flush();
    }
}
