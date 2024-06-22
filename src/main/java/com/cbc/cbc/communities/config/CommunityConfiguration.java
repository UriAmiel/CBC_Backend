package com.cbc.cbc.communities.config;

import com.cbc.cbc.communities.controller.CommunityController;
import com.cbc.cbc.communities.model.mapper.CommunityMapper;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.communities.service.CommunityService;
import com.cbc.cbc.communities.service.CommunityServiceImpl;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.users.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommunityConfiguration {

    @Bean
    public CommunityService communityService(CommunityRepository communityRepository, CommunityMapper communityMapper,
                                             UserRepository userRepository, RideMapper rideMapper) {
        return new CommunityServiceImpl(communityRepository, userRepository, communityMapper, rideMapper);
    }

    @Bean
    public CommunityController communityController(CommunityService communityService) {
        return new CommunityController(communityService);
    }
}
