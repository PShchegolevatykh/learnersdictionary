package com.pshchegolevatykh.learnersdictionary.persistence.dao;

import com.pshchegolevatykh.learnersdictionary.core.domain.User;

public interface UserDao {
    void create(User user);

    Iterable<User> findAll();

    User findByEmail(String email);

    User findById(int id);
}
