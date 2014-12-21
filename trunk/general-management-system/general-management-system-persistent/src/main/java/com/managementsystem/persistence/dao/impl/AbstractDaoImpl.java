package com.managementsystem.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.managementsystem.entity.BusinessEntity;
import com.managementsystem.persistence.dao.AbstractDao;

public abstract class AbstractDaoImpl<T extends BusinessEntity> implements
		AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		Assert.notNull(id, "And id must be provided but was [null].");
		Session session = getSession();
		Class<T> businessEntity = determineEntityType();
		T entity = null;
		try {
			entity = (T) session.get(businessEntity, id);
		} catch (IllegalArgumentException argumentException) {
			String message = String
					.format("The queried database table does not contain records of type [%s] or the id is not of type long.",
							businessEntity.getName());
			throw new DaoException(message, argumentException);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = getSession();
		Class<T> businessEntity = determineEntityType();
		List<T> entities = null;
		try {
			entities = session.createCriteria(businessEntity).list();
		} catch (IllegalArgumentException e) {
			String message = String
					.format("The queried database table does not contain records of type [%s]",
							businessEntity.getName());
			throw new DaoException(message, e);
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public T saveOrUpdate(T insertEntity) {
		Assert.notNull(insertEntity,
				"And insertEntity must be provided but was [null].");
		Session session = getSession();
		T entity = null;
		try {
			entity = (T) session.merge(determineEntityType().getName(),
					insertEntity);
		} catch (IllegalArgumentException argumentException) {
			String message = "It seems that the given object is not a valid entity.";
			throw new DaoException(message, argumentException);
		}
		return entity;
	}

	public void delete(T deleteEntity) {
		Assert.notNull(deleteEntity,
				"And deleteEntity must be provided but was [null].");
		Session session = getSession();
		try {
			session.delete(determineEntityType().getName(), deleteEntity);
		} catch (IllegalArgumentException argumentException) {
			String message = "It seems that the given object is not a valid entity.";
			throw new DaoException(message, argumentException);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> determineEntityType() {
		Type type = getClass().getGenericSuperclass();
		return (Class<T>) determineEntityType(type);
	}

	private Type determineEntityType(Type type) {
		if (type instanceof ParameterizedType) {
			return ((ParameterizedType) type).getActualTypeArguments()[0];
		} else {
			Type superType = ((Class<?>) type).getGenericSuperclass();
			return determineEntityType(superType);
		}
	}
}
