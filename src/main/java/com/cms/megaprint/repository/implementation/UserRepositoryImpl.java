package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.User;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryImpl extends CrudRepoHibernateImpl<User, Long> implements UserRepository {

    private final VarConfig varConfig;

    public UserRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil, VarConfig varConfig) {
        super(hibernateSessionFactoryUtil);
        this.varConfig = varConfig;
    }

    private Long getUserCount() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select count(u) from  User");
        Long result = (Long) query.getSingleResult();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<User> findAll() {
        if (getUserCount() == 0) {
            List<User> result = new ArrayList<>();
            User defaultUser = new User();
            defaultUser.setId(Long.valueOf(-1));
            defaultUser.setUsername(varConfig.getDefaultUsername());
            defaultUser.setPassword(varConfig.getDefaultPassword());
            result.add(defaultUser);
            return result;
        }
        return super.findAll();
    }

    private Optional<User> findInDbByUsername(String username) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select u from User u where u.username = '" + username + "'");
        Optional<User> result;
        if (query.list().size() == 1) {
            result = Optional.of((User) query.list().get(0));
        } else {
            result = Optional.empty();
        }
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public User save(User object) {
        if (findInDbByUsername(object.getUsername()).isPresent()) {
            return null;
        }
        return super.save(object);
    }

    @Override
    public User update(User object) {
        // if try to update default user which does not exist in real db
        if (object.getId() <= 0) {
            return save(object);
        }
        return super.update(object);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if (getUserCount() == 0) {
            if (varConfig.getDefaultUsername().equals(username)) {
                User defaultUser = new User();
                defaultUser.setId(Long.valueOf(-1));
                defaultUser.setUsername(varConfig.getDefaultUsername());
                defaultUser.setPassword(varConfig.getDefaultPassword());
                return Optional.of(defaultUser);
            } else {
                return Optional.empty();
            }
        }
        return findInDbByUsername(username);
    }
}
