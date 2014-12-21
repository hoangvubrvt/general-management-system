package com.managementsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managementsystem.entity.Customer;
import com.managementsystem.persistence.dao.CustomerDao;
import com.managementsystem.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private CustomerDao customerDao;
	
	@Transactional(readOnly=false)
	public Customer saveOrUpdate(Customer customer) {
		return customerDao.saveOrUpdate(customer);
	}

	@Transactional(readOnly=true)
	public Customer findById(long id) {
		return customerDao.findById(id);
	}

	@Transactional(readOnly=true)
	public Customer findById(Long id) {
		return customerDao.findById(id);
	}

	@Transactional(readOnly=true)
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}

	@Transactional(readOnly=false)
	public void delete(Customer deleteEntity) {
		customerDao.delete(deleteEntity);
	}
}
