package com.picpay.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.picpay.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user WHERE cpf = ?0", nativeQuery = true)
	boolean existsByCpf(String cpf);

	//@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user WHERE email = ?1", nativeQuery = true)
	boolean existsByEmail(String email);
	
	@Query(value = "select distinct u from User u left join u.accounts a where u.full_name like ?1% or a.username like ?1% ")
	List<User> findByFullnameOrUsername(String name);

}
