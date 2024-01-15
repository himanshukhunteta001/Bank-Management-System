package com.accountmanagementservice.repository;

import com.accountmanagementservice.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountRepository extends JpaRepository<AccountDetail,Integer> {

    List<AccountDetail> findByCustomerId(int customerId);
}
