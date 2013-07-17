package com.pshchegolevatykh.learnersdictionary.core.service.dozer;

import javax.ejb.Singleton;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

import com.pshchegolevatykh.learnersdictionary.core.domain.User;
import com.pshchegolevatykh.learnersdictionary.core.service.MapperService;
import com.pshchegolevatykh.learnersdictionary.persistence.entity.Category;
import com.pshchegolevatykh.learnersdictionary.persistence.entity.Entry;
import com.pshchegolevatykh.learnersdictionary.persistence.entity.Profile;

@Singleton
public class DozerMapperService implements MapperService {

	private DozerBeanMapper mapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T mapTo(Object source, Class<?> destinationClass) {
		return (T) mapper.map(source, destinationClass);
	}
	
	
	public DozerMapperService() {
		mapper = initMapper();
	}


	private DozerBeanMapper initMapper() {
		BeanMappingBuilder builder = new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(Category.class, com.pshchegolevatykh.learnersdictionary.core.domain.Category.class);
				mapping(Entry.class, com.pshchegolevatykh.learnersdictionary.core.domain.Entry.class);
				mapping(Profile.class, com.pshchegolevatykh.learnersdictionary.core.domain.Profile.class);
				mapping(User.class, com.pshchegolevatykh.learnersdictionary.core.domain.User.class);
			}
		};
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(builder);
		return mapper;
	}
}
