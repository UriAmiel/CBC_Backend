package com.cbc.cbc.communities.model.mapper;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.record.Community;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommunityMapperTest {

    private static final Long ADDED_COMMUNITY_CREATOR_ID = 1L;
    private static final String ADDED_COMMUNITY_NAME = "added-community-name";
    private static final String ADDED_COMMUNITY_DESC = "added-community-desc";
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
        assertEquals(ADDED_COMMUNITY_CREATOR_ID, community.getCreatorId());
        assertEquals(ADDED_COMMUNITY_NAME, community.getName());
        assertEquals(ADDED_COMMUNITY_DESC, community.getDescription());
        assertEquals(0, community.getMembers().size());
        assertNotNull(community.getCreatedAt());
    }

    private AddCommunityRequest getAddCommunityRequest() {
        return AddCommunityRequest.builder()
                .creatorId(ADDED_COMMUNITY_CREATOR_ID)
                .name(ADDED_COMMUNITY_NAME)
                .description(ADDED_COMMUNITY_DESC)
                .build();

    }

}