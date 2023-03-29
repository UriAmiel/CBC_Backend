package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.pojo.Community;

import java.util.List;

public interface CommunityService {
    public Community saveCommunity(Community community);
    public List<Community> getAllCommunities();

}
