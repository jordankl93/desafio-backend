package com.picpay.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.users.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
