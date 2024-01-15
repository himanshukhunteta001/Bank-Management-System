package com.customermanagementservice.service;


import com.customermanagementservice.entity.Customer;

import java.util.List;

public interface ICustomerService {

   public List<Customer> getAllCustomerDetail();

   public void addNewCustomer(Customer customer);

   public Customer getCustomerDetailById(int customerId);

   public void deleteCustomerDetail(int customerId);

   public void updateCustomerDetail(int customerId, Customer customer);



}
