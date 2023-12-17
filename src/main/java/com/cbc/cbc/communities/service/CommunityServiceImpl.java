package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.pojo.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private CommunityRepository communityRepository;

    @Override
    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }
}
