package com.project.uber.repository;

import com.project.uber.entity.RideBooked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideBookedRepository extends JpaRepository<RideBooked,Long> {
}
