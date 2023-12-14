package com.cbc.cbc.rides.config;

import com.cbc.cbc.rides.controller.RideController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RidesConfiguration {

    @Bean
    public RideController rideController() {
        return new RideController();
    }
}
