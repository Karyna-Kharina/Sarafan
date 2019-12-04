package org.homeproject.demo.sarafan.repository;

import org.homeproject.demo.sarafan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
