package com.managementsystem.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.managementsystem.entity.Customer;
import com.managementsystem.persistence.dao.CustomerDao;

@Repository(value = "customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Customer> implements CustomerDao {

}
