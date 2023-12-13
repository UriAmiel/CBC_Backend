package com.cbc.cbc.config;

import com.cbc.cbc.controller.RideController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RidesConfiguration {

    @Bean
    public RideController rideController() {
        return new RideController();
    }
}
