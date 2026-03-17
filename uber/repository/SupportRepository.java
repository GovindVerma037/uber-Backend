package com.project.uber.repository;

import com.project.uber.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);

}
