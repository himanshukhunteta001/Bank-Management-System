package com.accountmanagementservice.service.serviceImpl;

import com.accountmanagementservice.entity.AccountDetail;
import com.accountmanagementservice.entity.BalanceTransaction;
import com.accountmanagementservice.entity.Customer;
import com.accountmanagementservice.exception.ApplicationException;
import com.accountmanagementservice.repository.IAccountRepository;
import com.accountmanagementservice.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addMoneyToAccount(BalanceTransaction balanceTransaction) {
        try {
            AccountDetail accountDetail = accountRepository.findById(balanceTransaction.getAccountId()).orElseThrow(() -> new ApplicationException("Account id is not available."));

            if (accountDetail != null) {
                Customer customer = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/profile/getCustomerDetailById/" + balanceTransaction.getCustomerId(), Customer.class);

                if (customer != null) {
                    int newBalance = accountDetail.getBalance() + balanceTransaction.getBalance();
                    accountDetail.setBalance(newBalance);
                    accountRepository.save(accountDetail);
                } else {
                    throw new IllegalArgumentException("Customer not found. Please provide valid customer Details.");
                }
            } else {
                throw new IllegalArgumentException("Account not found. Please provide a valid account Details.");
            }
        } catch (Exception ex) {
            throw new RuntimeException("There is error while adding money from the account.", ex);
        }


    }

    @Override
    public AccountDetail getAccountDetails(int accountId) {
        try {
            AccountDetail accountDetail = accountRepository.findById(accountId).orElseThrow(() ->
                    new ApplicationException("Account id is not available."));

            Customer customer = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/profile/" + accountDetail.getCustomerId(), Customer.class);

            if (customer != null) {
                accountDetail.setCustomer(customer);
            } else {
                throw new IllegalArgumentException("Customer details not available for accountId: " + accountId);
            }

            return accountDetail;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching account details", e);
        }
    }

    @Override
    public void createAccount(AccountDetail accountDetail) {
        int customerId = accountDetail.getCustomerId();
        try {
            Customer customer = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/profile/" + customerId, Customer.class);
            if (customer != null) {
                accountRepository.save(accountDetail);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating account", e);
        }
    }

    @Override
    public List<AccountDetail> getAllAccountDetails() {
        try {
            return accountRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching account details", e);
        }
    }

    @Override
    public void withdrawMoneyToAccount(BalanceTransaction balanceTransaction) {
        try {
            AccountDetail accountDetail = accountRepository.findById(balanceTransaction.getAccountId()).orElseThrow(() -> new ApplicationException("Account id is not available"));

            if (accountDetail != null) {
                Customer customer = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/profile/getCustomerDetailById/" + balanceTransaction.getCustomerId(), Customer.class);

                if (customer != null) {
                    int withdrawnMoney = balanceTransaction.getBalance();

                    if (accountDetail.getBalance() >= withdrawnMoney) {
                        int newBalance = accountDetail.getBalance() - withdrawnMoney;
                        accountDetail.setBalance(newBalance);
                        accountRepository.save(accountDetail);
                    } else {
                        throw new IllegalArgumentException("Insufficient Balance");
                    }
                } else {
                    throw new IllegalArgumentException("Customer not found. Please provide valid customer Details.");
                }
            } else {
                throw new IllegalArgumentException("Account not found. Please provide a valid account Details.");
            }
        } catch (Exception ex) {
            throw new RuntimeException("There is error while withdrawing the money from account", ex);
        }
    }

    @Override
    public boolean deleteAccountId(int accountId) {
        try {
            Optional<AccountDetail> accountDetail = accountRepository.findById(accountId);
            if (accountDetail.isPresent()) {
                accountRepository.deleteById(accountId);
                return true;
            } else {
                throw new IllegalArgumentException("Account not found. Please provide a valid account Details.");
            }
        } catch (Exception ex) {
            throw new RuntimeException("There is an error while deleting an account.");
        }
    }

    @Override
    public List<AccountDetail> getAccountDetailByCustomerId(int customerId) {
        try {
            Customer customer = restTemplate.getForObject("http://CUSTOMER-MANAGEMENT-SERVICE/customer/profile/" + customerId, Customer.class);
            if (customer == null) {
                throw new RuntimeException("Customer id is not available.");
            }
              return  accountRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching account details using customer id:", e);
        }
    }



    @Override
    public void deleteAccountByCustomerId(int customerId) {
        try {
            List<AccountDetail> accountDetails = accountRepository.findByCustomerId(customerId);
            if (accountDetails != null) {

                for (AccountDetail detail : accountDetails) {
                    accountRepository.delete(detail);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting account details", e);
        }
    }
}
