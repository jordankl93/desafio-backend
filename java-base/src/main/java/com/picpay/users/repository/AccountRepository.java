package com.picpay.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.users.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	boolean existsByUsername(String username);
	
	List<Account> findByUser_Id(Long user_id);

}
