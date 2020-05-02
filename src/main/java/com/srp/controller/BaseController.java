package com.srp.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.srp.service.BaseService;

/**
 * Skeleton Base Controller for all the Rest Controller.
 *
 * @param <T>
 * @param <E>
 * @param <PK>
 */
public class BaseController<T, E, P extends Serializable> {

	/**
	 * Variable dtoClass
	 */
	protected Class<T> dtoClass;

	/**
	 * Variable baseService
	 */
	BaseService<T, E, P> baseService;

	/**
	 * Variable entityClass
	 */
	protected Class<E> entityClass;

	/**
	 * @param service
	 */
	@SuppressWarnings("unchecked")
	public BaseController(BaseService<T, E, P> service) {
		this.baseService = service;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.dtoClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	/**
	 * @param id
	 * @return
	 */
	public T find(P id) {
		return baseService.find(id);
	}

	/**
	 * @return List of Type T
	 */
	public List<T> findAll() {
		return baseService.findAll();
	}

	/**
	 * @return
	 */
	public List<T> getAllDistinct() {
		return baseService.getAllDistinct();
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean exists(P id) {
		return baseService.exists(id);
	}

	/**
	 * @param object
	 * @return
	 */
	public T save(T object) {
		return baseService.save(object);
	}

	/**
	 * @param object
	 */
	public void remove(T object) {
		baseService.remove(object);
	}

	/**
	 * @param id
	 */
	public void remove(P id) {
		baseService.remove(id);
	}

	/**
	 * @return
	 */
	public BaseService<T, E, P> getBaseService() {
		return baseService;

	}

}
