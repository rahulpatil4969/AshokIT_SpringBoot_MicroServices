package com.ait.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ait.login.entity.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
	
	@Query(value = "from UserLogin login where login.username = ?1")
	Optional<UserLogin>  fetchUser(String username);

}
