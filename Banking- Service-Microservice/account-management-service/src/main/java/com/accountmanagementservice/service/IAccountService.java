package com.accountmanagementservice.service;

import com.accountmanagementservice.entity.AccountDetail;
import com.accountmanagementservice.entity.BalanceTransaction;

import java.util.List;

public interface IAccountService {

    void addMoneyToAccount(BalanceTransaction balanceTransaction);
    AccountDetail getAccountDetails(int accountId);

    void createAccount(AccountDetail accountDetail);

    List<AccountDetail> getAllAccountDetails();

    void withdrawMoneyToAccount(BalanceTransaction balanceTransaction);

    boolean deleteAccountId(int accountId);

    List<AccountDetail> getAccountDetailByCustomerId(int customerId);

    void deleteAccountByCustomerId(int customerId);

}
