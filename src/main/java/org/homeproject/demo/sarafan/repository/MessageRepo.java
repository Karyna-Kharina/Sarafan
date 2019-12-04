package org.homeproject.demo.sarafan.repository;

import org.homeproject.demo.sarafan.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
