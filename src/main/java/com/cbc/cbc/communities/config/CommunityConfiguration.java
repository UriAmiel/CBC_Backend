package com.cbc.cbc.communities.config;

import com.cbc.cbc.communities.controller.CommunityController;
import com.cbc.cbc.communities.model.add_community.mapper.CommunityMapper;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.communities.service.CommunityService;
import com.cbc.cbc.communities.service.CommunityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommunityConfiguration {

    @Bean
    public CommunityService communityService(CommunityRepository communityRepository) {
        return new CommunityServiceImpl(communityRepository);
    }

    @Bean
    public CommunityController communityController(CommunityService communityService, CommunityMapper addCommunityRequestMapper) {
        return new CommunityController(communityService, addCommunityRequestMapper);
    }
}
