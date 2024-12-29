package com.demo.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.api.model.User;

@Repository
public interface UserRegistrationandloginRepository extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.email =?1")
	User findByEmail(String email);
}
