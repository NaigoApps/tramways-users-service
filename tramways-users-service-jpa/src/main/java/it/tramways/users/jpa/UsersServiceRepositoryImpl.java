package it.tramways.users.jpa;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.outbound.UsersServiceRepository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class UsersServiceRepositoryImpl implements UsersServiceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String user) {
        CriteriaQuery<User> query = query(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(cb().equal(root.get(User_.username), user));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Long countUsers(Role role) {
        CriteriaQuery<Long> query = query(Long.class);
        query.select(cb().count(query.from(User.class)));
        query.where(cb().isMember(role, query.from(User.class).get(User_.ROLES)));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void deleteByUuid(String id) {
        User user = findByUuid(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User findByUuid(String id) {
        CriteriaQuery<User> query = query(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(cb().equal(root.get(BaseEntity_.uuid), id));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    private <T> CriteriaQuery<T> query(Class<T> clazz) {
        return entityManager.getCriteriaBuilder().createQuery(clazz);
    }

    private CriteriaBuilder cb() {
        return entityManager.getCriteriaBuilder();
    }

}
