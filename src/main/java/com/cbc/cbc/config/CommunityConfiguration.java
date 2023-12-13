package com.cbc.cbc.config;

import com.cbc.cbc.controller.CommunityController;
import com.cbc.cbc.service.CommunityService;
import com.cbc.cbc.service.CommunityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommunityConfiguration {

    @Bean
    public CommunityService communityService() {
        return new CommunityServiceImpl();
    }

    @Bean
    public CommunityController communityController(CommunityService communityService) {
        return new CommunityController(communityService);
    }
}
