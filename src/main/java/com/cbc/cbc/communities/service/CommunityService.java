package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.rides.model.dto.RideDTO;

import java.util.List;

public interface CommunityService {
    CommunityDTO addCommunity(AddCommunityRequest communityToAdd);
    List<CommunityDTO> getAllCommunities();

    List<RideDTO> getRidesOfCommunity(int communityId);

}
