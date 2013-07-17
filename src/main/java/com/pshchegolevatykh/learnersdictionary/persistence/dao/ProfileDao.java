package com.pshchegolevatykh.learnersdictionary.persistence.dao;

import com.pshchegolevatykh.learnersdictionary.core.domain.Profile;

public interface ProfileDao {
	void create(Profile profile);
	void update(Profile profile);
	Iterable<Profile> findAll();
}
