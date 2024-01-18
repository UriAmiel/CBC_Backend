package com.cbc.cbc.communities.controller;

import com.cbc.cbc.communities.model.add_community.CommunityResponse;
import com.cbc.cbc.communities.model.add_community.mapper.CommunityMapper;
import com.cbc.cbc.communities.pojo.Community;
import com.cbc.cbc.communities.service.CommunityService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommunityControllerTest {

    private final CommunityMapper communityMapper = mock(CommunityMapper.class);
    private final CommunityService communityService = mock(CommunityService.class);
    private final static Community COMMUNITY1 = Community.builder().id(1).build();
    private final static Community COMMUNITY2 = Community.builder().id(2).build();
    private final static Community COMMUNITY3 = Community.builder().id(3).build();
    private CommunityController communityController;

    @BeforeEach
    public void setUp() {
        communityController = new CommunityController(communityService, communityMapper);
    }

    @Test
    public void testGetAllCommunities_emptyCommunityList() {
        List<Community> expectedCommunities = Lists.newArrayList();
        when(communityService.getAllCommunities()).thenReturn(expectedCommunities);

        ResponseEntity<List<CommunityResponse>> response = communityController.getAllCommunities();
        HttpStatus statusCode = response.getStatusCode();

        assertEquals(200, statusCode.value());
        List<Integer> responseIds = convertResponseToIdsList(Objects.requireNonNull(response.getBody()));
        assertEquals(getExpectedCommunitiesIds(expectedCommunities), responseIds);
    }

    @Test
    public void testGetAllCommunities_filledCommunityList() {
        List<Community> expectedCommunities = getCommunitiesList();
        mockCommunityResponse();
        when(communityService.getAllCommunities()).thenReturn(expectedCommunities);

        ResponseEntity<List<CommunityResponse>> response = communityController.getAllCommunities();
        HttpStatus statusCode = response.getStatusCode();

        assertEquals(200, statusCode.value());
        List<Integer> responseIds = convertResponseToIdsList(Objects.requireNonNull(response.getBody()));
        assertEquals(getExpectedCommunitiesIds(expectedCommunities), responseIds);
    }

    private List<Community> getCommunitiesList() {
        return Lists.list(COMMUNITY1, COMMUNITY2, COMMUNITY3);
    }

    private void mockCommunityResponse() {
        when(communityMapper.toCommunityResponse(COMMUNITY1)).thenReturn(CommunityResponse.builder().id(COMMUNITY1.getId()).build());
        when(communityMapper.toCommunityResponse(COMMUNITY2)).thenReturn(CommunityResponse.builder().id(COMMUNITY2.getId()).build());
        when(communityMapper.toCommunityResponse(COMMUNITY3)).thenReturn(CommunityResponse.builder().id(COMMUNITY3.getId()).build());
    }

    private List<Integer> getExpectedCommunitiesIds(List<Community> communities) {
        return communities.stream()
                .map(Community::getId)
                .toList();
    }

    private List<Integer> convertResponseToIdsList(List<CommunityResponse> communityResponses) {
        return communityResponses.stream()
                .map(CommunityResponse::getId)
                .toList();
    }
}