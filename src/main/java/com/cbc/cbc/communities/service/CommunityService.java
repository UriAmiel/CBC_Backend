package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;

import java.util.List;

public interface CommunityService {
    CommunityDTO addCommunity(AddCommunityRequest communityToAdd);

    List<CommunityDTO> getAllCommunities();

}
