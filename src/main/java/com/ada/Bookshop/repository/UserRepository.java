package com.ada.Bookshop.repository;


import com.ada.Bookshop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    @Override
    @Query(value = "SELECT * FROM CLIENTS WHERE ACTIVE = TRUE", nativeQuery = true)
    Page<User> findAll(Pageable pageable);

    List<User> findAllByName(String name);

    UserDetails findByEmail(String email);

}
