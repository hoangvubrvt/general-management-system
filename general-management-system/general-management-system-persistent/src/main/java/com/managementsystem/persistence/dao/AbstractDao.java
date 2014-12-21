package com.managementsystem.persistence.dao;

import java.util.List;

import com.managementsystem.entity.BusinessEntity;

public interface AbstractDao<T extends BusinessEntity> {
	public T findById(Long id);

	public  List<T> findAll();

	public T saveOrUpdate(T insertEntity);

	public void delete(T deleteEntity);
}
