package com.cbc.cbc.rides.config;

import com.cbc.cbc.rides.controller.RideController;
import com.cbc.cbc.rides.model.mapper.RideMapper;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.rides.service.RideService;
import com.cbc.cbc.rides.service.RideServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RideConfiguration {

    @Bean
    public RideService rideService(RideRepository rideRepository, RideMapper rideMapper) {
        return new RideServiceImpl(rideRepository, rideMapper);
    }

    @Bean
    public RideController rideController(RideService rideService, RideMapper rideMapper) {
        return new RideController(rideService, rideMapper);
    }
}
