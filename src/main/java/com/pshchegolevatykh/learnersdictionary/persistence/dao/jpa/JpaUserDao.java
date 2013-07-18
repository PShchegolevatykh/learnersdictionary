package com.pshchegolevatykh.learnersdictionary.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pshchegolevatykh.learnersdictionary.core.domain.User;
import com.pshchegolevatykh.learnersdictionary.core.service.MapperService;
import com.pshchegolevatykh.learnersdictionary.persistence.dao.UserDao;

@Stateless
public class JpaUserDao implements UserDao {
    
    @PersistenceContext(unitName = "learnersDictionary")
    private EntityManager entityManager;

    @EJB
    private MapperService mapperService;
    
    @Override
    public void create(User user) {
	final com.pshchegolevatykh.learnersdictionary.persistence.entity.User userEntity = mapperService.mapTo(user, com.pshchegolevatykh.learnersdictionary.persistence.entity.User.class);
	entityManager.persist(userEntity);
    }

    @Override
    public Iterable<User> findAll() {
	List<User> userList = new ArrayList<User>();
	Query query = entityManager.createQuery("select u from User u");
	List<com.pshchegolevatykh.learnersdictionary.persistence.entity.User> userEntityList = (List<com.pshchegolevatykh.learnersdictionary.persistence.entity.User>)query.getResultList();
	for (com.pshchegolevatykh.learnersdictionary.persistence.entity.User userEntity : userEntityList) {
	    User user = mapperService.mapTo(userEntity, User.class);
	    userList.add(user);
	}
	return userList;
    }

    @Override
    public User findByEmail(String email) {
	User user;
	Query query = entityManager.createQuery("select u from User u where u.email = :email");
	query.setParameter("email", email);
	com.pshchegolevatykh.learnersdictionary.persistence.entity.User userEntity = (com.pshchegolevatykh.learnersdictionary.persistence.entity.User)query.getSingleResult();
	user = mapperService.mapTo(userEntity, User.class);
	return user;
    }

    @Override
    public User findById(int id) {
	User user;
	Query query = entityManager.createQuery("select u from User u where u.id = :id");
	query.setParameter("id", id);
	com.pshchegolevatykh.learnersdictionary.persistence.entity.User userEntity = (com.pshchegolevatykh.learnersdictionary.persistence.entity.User)query.getSingleResult();
	user = mapperService.mapTo(userEntity, User.class);
	return user;
    }

}
