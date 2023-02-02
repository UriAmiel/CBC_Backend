package com.cbc.cbc.service;

import com.cbc.cbc.pojo.Community;
import com.cbc.cbc.repository.CommunityRepository;
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
