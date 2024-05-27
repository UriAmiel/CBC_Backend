package com.cbc.cbc.communities.model.dto.mapper;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.record.Community;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    Community toCommunity(AddCommunityRequest addCommunityRequest);

    CommunityDTO toCommunityDTO(Community community);
}
