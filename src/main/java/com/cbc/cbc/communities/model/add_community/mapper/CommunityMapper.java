package com.cbc.cbc.communities.model.add_community.mapper;

import com.cbc.cbc.communities.model.add_community.AddCommunityRequest;
import com.cbc.cbc.communities.model.add_community.CommunityResponse;
import com.cbc.cbc.communities.pojo.Community;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    Community toCommunity(AddCommunityRequest addCommunityRequest);

    CommunityResponse toCommunityResponse(Community community);
}
