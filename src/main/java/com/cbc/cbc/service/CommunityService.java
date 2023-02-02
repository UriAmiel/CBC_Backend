package com.cbc.cbc.service;

import com.cbc.cbc.pojo.Community;

import java.util.List;

public interface CommunityService {
    public Community saveCommunity(Community community);
    public List<Community> getAllCommunities();

}
