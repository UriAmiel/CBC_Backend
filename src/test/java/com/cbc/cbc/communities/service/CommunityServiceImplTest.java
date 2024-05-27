package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.model.dto.mapper.CommunityMapper;
import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommunityServiceImplTest {
    private static final int COMMUNITY1_ID = 1;
    private static final int COMMUNITY2_ID = 2;
    private CommunityMapper mapper;
    private CommunityRepository repository;
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        repository = mock(CommunityRepository.class);
        mapper = mock(CommunityMapper.class);
        communityService = new CommunityServiceImpl(repository, mapper);
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
        when(repository.findAll()).thenReturn(communities);
        when(mapper.toCommunityDTO(community1)).thenReturn(getCommunityDTO(1));
        when(mapper.toCommunityDTO(community2)).thenReturn(getCommunityDTO(2));

        List<CommunityDTO> allCommunities = communityService.getAllCommunities();

        assertEquals(2, allCommunities.size());
    }

    private Community getCommunity(int communityId) {
        return Community.builder()
                .id(communityId)
                .build();
    }

    private CommunityDTO getCommunityDTO(int communityId) {
        return CommunityDTO.builder()
                .id(communityId)
                .build();
    }
}