package com.xsslong.weflux.multiple.dao;

import com.xsslong.weflux.multiple.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JpaUserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public Long insert(final User user) {
        entityManager.persist(user);
        return user.getUserId();
    }

    public void delete(final Long userId) {
        Query query = entityManager.createQuery("DELETE FROM UserEntity o WHERE o.userId = ?1");
        query.setParameter(1, userId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<User> selectAll() {
        return (List<User>) entityManager.createQuery("SELECT o FROM UserEntity o").getResultList();
    }

    @SuppressWarnings("unchecked")
    public User selectOne(final Long userId) {
        Query query = entityManager.createQuery("SELECT o FROM UserEntity o WHERE o.userId = ?1");
        query.setParameter(1, userId);
        return (User) query.getSingleResult();
    }
}
