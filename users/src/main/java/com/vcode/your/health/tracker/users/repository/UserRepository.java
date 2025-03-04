package com.vcode.your.health.tracker.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcode.your.health.tracker.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User save(User user);


	public Optional<User> findByMobile(String mobile);

}
