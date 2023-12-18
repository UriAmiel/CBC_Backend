package com.cbc.cbc.communities.model.mapper;

import com.cbc.cbc.communities.model.AddCommunityRequest;
import com.cbc.cbc.communities.pojo.Community;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    Community toCommunity(AddCommunityRequest addCommunityRequest);

    AddCommunityResponse toCommunityResponse(Community community);
}
