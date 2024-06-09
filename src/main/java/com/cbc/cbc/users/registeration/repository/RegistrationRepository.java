package com.cbc.cbc.users.registeration.repository;

import com.cbc.cbc.users.record.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {
}
