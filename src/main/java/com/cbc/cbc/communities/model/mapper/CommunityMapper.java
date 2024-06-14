package com.cbc.cbc.communities.model.mapper;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.record.Community;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Community toCommunity(AddCommunityRequest addCommunityRequest);

    @Mapping(target = "membersSize", ignore = true)
    CommunityDTO toCommunityDTO(Community community);

    @AfterMapping
    default void mapMembers(Community community, @MappingTarget CommunityDTO communityDTO) {
        if (community.getMembers() != null) {
            long communitySize = community.getMembers().size();
            communityDTO.setMembersSize(communitySize);
        }
    }
}
