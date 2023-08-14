package com.example.polyfood.repository;

import com.example.polyfood.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
}
