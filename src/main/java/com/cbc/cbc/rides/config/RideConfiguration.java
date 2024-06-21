package com.cbc.cbc.rides.config;

import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.rides.controller.RideController;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.rides.service.RideService;
import com.cbc.cbc.rides.service.RideServiceImpl;
import com.cbc.cbc.users.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RideConfiguration {

    @Bean
    public RideService rideService(RideRepository rideRepository, UserRepository userRepository,
                                   CommunityRepository communityRepository, RideMapper rideMapper) {
        return new RideServiceImpl(rideRepository, userRepository, communityRepository, rideMapper);
    }

    @Bean
    public RideController rideController(RideService rideService) {
        return new RideController(rideService);
    }
}
