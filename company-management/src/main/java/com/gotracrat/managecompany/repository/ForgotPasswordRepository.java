package com.gotracrat.managecompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gotracrat.managecompany.entity.AspnetMembership;
@Transactional
public interface ForgotPasswordRepository extends JpaRepository<AspnetMembership, Long>{
	Optional<AspnetMembership> findByEmail(String email);
    Optional<AspnetMembership> findByResetToken(String resetToken);

}
