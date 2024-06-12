package com.cbc.cbc.communities.model.mapper;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.record.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Community toCommunity(AddCommunityRequest addCommunityRequest);

    CommunityDTO toCommunityDTO(Community community);
}
