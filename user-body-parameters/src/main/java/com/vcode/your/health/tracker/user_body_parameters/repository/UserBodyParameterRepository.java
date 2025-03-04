package com.vcode.your.health.tracker.user_body_parameters.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcode.your.health.tracker.user_body_parameters.entiry.UserBodyParameter;

@Repository
public interface UserBodyParameterRepository extends JpaRepository<UserBodyParameter, Long> {

	Optional<UserBodyParameter> findByMobile(String mobile);

}
