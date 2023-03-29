package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.communities.pojo.Community;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
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
