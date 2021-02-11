package it.tramways.users.jpa;

import it.tramways.users.core.Role;
import it.tramways.users.core.User;
import it.tramways.users.jpa.mappers.RoleMapper;
import it.tramways.users.jpa.mappers.UserMapper;
import it.tramways.users.outbound.UsersRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    private EntityManager entityManager;

    private final UsersJpaRepository delegate;

    public UsersRepositoryImpl(EntityManager entityManager, UsersJpaRepository delegate,
        UserMapper userMapper, RoleMapper roleMapper) {
        this.entityManager = entityManager;
        this.delegate = delegate;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public User findByUsername(String user) {
        UserEntity example = new UserEntity();
        example.setUuid(null);
        example.setUsername(user);
        return userMapper.map(delegate.findOne(Example.of(example)).orElse(null));
    }

    @Override
    public Long countByRole(Role role) {
        CriteriaQuery<Long> query = query(Long.class);
        query.select(cb().count(query.from(UserEntity.class)));
        query.where(cb().isMember(roleMapper.map(role), query.from(UserEntity.class).get(UserEntity_.ROLES)));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public User createUser(User user) {
        UserEntity newUser = userMapper.map(user);
        return userMapper.map(delegate.save(newUser));
    }

    @Override
    public void deleteByUuid(String uuid) {
        delegate.findOne(withUuid(uuid)).ifPresent(delegate::delete);
    }

    @Override
    public void editPassword(String uuid, String newPassword) {
        delegate.findOne(withUuid(uuid)).ifPresent(entity -> entity.setPassword(newPassword));
    }

    @Override
    public void editRoles(String uuid, Set<Role> newRoles) {
        delegate.findOne(withUuid(uuid))
            .ifPresent(entity -> entity.setRoles(newRoles.stream().map(roleMapper::map).collect(
                Collectors.toSet())));
    }

    @Override
    public void editEnabling(String uuid, boolean enabled) {
        delegate.findOne(withUuid(uuid)).ifPresent(entity -> entity.setEnabled(enabled));
    }

    @Override
    public User findByUuid(String id) {
        return userMapper.map(delegate.findOne(withUuid(id)).orElse(null));
    }

    @Override
    public List<User> findAll() {
        return delegate.findAll().stream().map(userMapper::map).collect(Collectors.toList());
    }

    private Example<UserEntity> withUuid(String uuid) {
        UserEntity example = new UserEntity();
        example.setUuid(uuid);
        return Example.of(example);
    }

    private <T> CriteriaQuery<T> query(Class<T> clazz) {
        return entityManager.getCriteriaBuilder().createQuery(clazz);
    }

    private CriteriaBuilder cb() {
        return entityManager.getCriteriaBuilder();
    }

}
