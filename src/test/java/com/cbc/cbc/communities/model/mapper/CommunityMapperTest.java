package com.cbc.cbc.communities.model.mapper;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.record.Community;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommunityMapperTest {

    private static final Long CREATOR_ID = 1L;
    private static final String COMMUNITY_NAME = "added-community-name";
    private static final String COMMUNITY_DESC = "added-community-desc";
    private CommunityMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new CommunityMapperImpl();
    }

    @Test
    public void testToCommunity() {
        AddCommunityRequest addCommunityRequest = getAddCommunityRequest();
        Community community = mapper.toCommunity(addCommunityRequest);

        assertNotNull(community);
        assertEquals(CREATOR_ID, community.getCreatorId());
        assertEquals(COMMUNITY_NAME, community.getName());
        assertEquals(COMMUNITY_DESC, community.getDescription());
        assertEquals(0, community.getMembers().size());
        assertNotNull(community.getCreatedAt());
    }

    @Test
    public void testToCommunityDTO() {
        Community community = getCommunity();
        CommunityDTO communityDTO = mapper.toCommunityDTO(community);

        assertNotNull(communityDTO);
        assertEquals(CREATOR_ID, communityDTO.getCreatorId());
        assertEquals(COMMUNITY_NAME, communityDTO.getName());
        assertEquals(COMMUNITY_DESC, communityDTO.getDescription());
        assertEquals(0, communityDTO.getMembersSize());
    }

    private AddCommunityRequest getAddCommunityRequest() {
        return AddCommunityRequest.builder()
                .creatorId(CREATOR_ID)
                .name(COMMUNITY_NAME)
                .description(COMMUNITY_DESC)
                .build();

    }

    private Community getCommunity() {
        return Community.builder()
                .creatorId(CREATOR_ID)
                .name(COMMUNITY_NAME)
                .description(COMMUNITY_DESC)
                .members(new HashSet<>())
                .build();
    }

}