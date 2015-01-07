package com.managementsystem.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.managementsystem.entity.Customer;
import com.managementsystem.service.UserService;

@ManagedBean(name = "helloView")
@RequestScoped
public class HelloView {

	@ManagedProperty(value = "#{userService}")
	UserService userService;

	private Customer customer;

	@PostConstruct
	public void init() {
		this.customer = userService.findById(1L);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
