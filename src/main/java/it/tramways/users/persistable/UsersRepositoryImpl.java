package it.tramways.users.persistable;

import it.tramways.core.model.persistable.TramwaysRepositoryImpl;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl extends TramwaysRepositoryImpl<User> implements UsersRepository {

    private final EntityManager entityManager;

    public UsersRepositoryImpl(EntityManager em) {
        super(User.class, em);
        this.entityManager = em;
    }

    @Override
    public User create(User u) {
        entityManager.persist(u);
        return u;
    }

    @Override
    public User findByUsername(String user) {
        CriteriaQuery<User> query = query();
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
}
