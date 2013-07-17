package com.pshchegolevatykh.learnersdictionary.core.service;

public interface MapperService {
	<T> T mapTo(Object source, Class<?> destinationClass);
}
