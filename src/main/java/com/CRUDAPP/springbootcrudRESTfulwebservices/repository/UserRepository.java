package com.CRUDAPP.springbootcrudRESTfulwebservices.repository;

import com.CRUDAPP.springbootcrudRESTfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}