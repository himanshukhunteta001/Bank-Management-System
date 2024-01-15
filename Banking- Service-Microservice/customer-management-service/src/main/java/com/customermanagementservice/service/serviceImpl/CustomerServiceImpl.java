package com.customermanagementservice.service.serviceImpl;

import com.customermanagementservice.entity.Customer;
import com.customermanagementservice.exception.ApplicationException;
import com.customermanagementservice.repository.ICustomerRepository;
import com.customermanagementservice.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Customer> getAllCustomerDetail() {
        try {
            return customerRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while fetching the customer details.", ex);
        }

    }

    @Override
    public void addNewCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while adding new customer.", ex);
        }
    }

    @Override
    public Customer getCustomerDetailById(int customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(()->new ApplicationException("Customer id not found"));
            return customer;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while fetching customer.", ex);
        }
    }

    @Override
    public void deleteCustomerDetail(int customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                    new ApplicationException("Customer not found with id: " + customerId));
            restTemplate.delete("http://ACCOUNT-MANAGEMENT-SERVICE/account/profile/deleteAccountByCustomerId/" + customer.getCustomerId());
            customerRepository.delete(customer);
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while deleting customer details", ex);
        }

    }

    @Override
    public void updateCustomerDetail(int customerId, Customer customer) {
        try {
            Customer getCustomer = customerRepository.findById(customerId).orElseThrow(() ->
                    new ApplicationException("Customer not found with id: " + customerId));

            getCustomer.setFirstName(customer.getFirstName());
            getCustomer.setLastName(customer.getLastName());
            getCustomer.setAccountNumber(customer.getAccountNumber());
            getCustomer.setAddress(customer.getAddress());
            getCustomer.setPhoneNumber(customer.getPhoneNumber());

            customerRepository.save(getCustomer);
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while updating customer details", ex);
        }
    }
}