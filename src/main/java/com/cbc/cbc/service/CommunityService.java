package com.cbc.cbc.service;

import com.cbc.cbc.pojo.Community;

import java.util.List;

public interface CommunityService {
    Community saveCommunity(Community community);
    List<Community> getAllCommunities();

}
