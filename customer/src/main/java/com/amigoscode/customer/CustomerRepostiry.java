package com.amigoscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepostiry extends JpaRepository<Customer, Integer>{
}
