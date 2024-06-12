package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.model.mapper.CommunityMapper;
import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.users.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CommunityServiceImplTest {
    private static final int COMMUNITY1_ID = 1;
    private static final int COMMUNITY2_ID = 2;
    private static final Long CREATOR_ID = 3L;
    private static final String ADDED_COMMUNITY_NAME = "added-community-name";
    private static final String ADDED_COMMUNITY_DESC = "added-community-desc";
    private CommunityMapper mapper;
    private CommunityRepository communityRepository;
    private UserRepository userRepository;
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        communityRepository = mock(CommunityRepository.class);
        userRepository = mock(UserRepository.class);
        mapper = mock(CommunityMapper.class);
        communityService = new CommunityServiceImpl(communityRepository, userRepository, mapper);
    }

    @Test
    public void testGetAllCommunities_emptyCommunityList() {
        List<CommunityDTO> allCommunities = communityService.getAllCommunities();
        assertEquals(0, allCommunities.size());
    }

    @Test
    public void testGetAllCommunities_communitiesExists() {
        Community community1 = getCommunity(COMMUNITY1_ID);
        Community community2 = getCommunity(COMMUNITY2_ID);
        List<Community> communities = Lists.list(community1, community2);
        when(communityRepository.findAll()).thenReturn(communities);
        when(mapper.toCommunityDTO(community1)).thenReturn(getCommunityDTO(1));
        when(mapper.toCommunityDTO(community2)).thenReturn(getCommunityDTO(2));

        List<CommunityDTO> allCommunities = communityService.getAllCommunities();

        assertEquals(2, allCommunities.size());
    }

    @Test
    public void testAddCommunity_happyFlow_expectSavedCommunity() {
        Community community = getCommunity(COMMUNITY1_ID);
        AddCommunityRequest addCommunityRequest = getAddCommunityRequest();
        when(mapper.toCommunity(addCommunityRequest)).thenReturn(community);
        when(mapper.toCommunityDTO(community)).thenReturn(getCommunityDTO(COMMUNITY1_ID));

        CommunityDTO communityDTO = communityService.addCommunity(addCommunityRequest);

        assertNotNull(communityDTO);
        verify(communityRepository).save(community);
        verify(userRepository).findById(CREATOR_ID);
    }

    private Community getCommunity(int communityId) {
        return Community.builder()
                .id(communityId)
                .creatorId(CREATOR_ID)
                .build();
    }

    private CommunityDTO getCommunityDTO(int communityId) {
        return CommunityDTO.builder()
                .id(communityId)
                .build();
    }

    private AddCommunityRequest getAddCommunityRequest() {
        return AddCommunityRequest.builder()
                .creatorId(CREATOR_ID)
                .name(ADDED_COMMUNITY_NAME)
                .description(ADDED_COMMUNITY_DESC)
                .build();
    }
}