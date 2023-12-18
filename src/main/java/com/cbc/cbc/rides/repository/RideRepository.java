package com.cbc.cbc.rides.repository;

import com.cbc.cbc.rides.pojo.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {}
