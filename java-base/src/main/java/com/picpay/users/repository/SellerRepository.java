package com.picpay.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.users.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
