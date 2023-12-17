package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.pojo.Community;

import java.util.List;

public interface CommunityService {
    Community saveCommunity(Community community);
    List<Community> getAllCommunities();

}
