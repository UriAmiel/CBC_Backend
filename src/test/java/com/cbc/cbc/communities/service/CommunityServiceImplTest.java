package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.model.mapper.CommunityMapper;
import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CommunityServiceImplTest {
    private static final int COMMUNITY_ID = 1;
    private static final Long CREATOR_ID = 3L;
    private static final String ADDED_COMMUNITY_NAME = "added-community-name";
    private static final String ADDED_COMMUNITY_DESC = "added-community-desc";
    private CommunityMapper communityMapper;
    private RideMapper rideMapper;
    private CommunityRepository communityRepository;
    private UserRepository userRepository;
    private CommunityService communityService;

    @BeforeEach
    public void setUp() {
        communityRepository = mock(CommunityRepository.class);
        userRepository = mock(UserRepository.class);
        communityMapper = mock(CommunityMapper.class);
        rideMapper = mock(RideMapper.class);
        communityService = new CommunityServiceImpl(communityRepository, userRepository, communityMapper, rideMapper);
    }

    @Test
    public void testGetAllCommunities_emptyCommunityList() {
        List<CommunityDTO> allCommunities = communityService.getAllCommunities();
        assertEquals(0, allCommunities.size());
    }

    @Test
    public void testGetAllCommunities_communitiesExists() {
        Community community1 = getCommunity(COMMUNITY_ID, new HashSet<>());
        Community community2 = getCommunity(2, new HashSet<>());
        List<Community> communities = Lists.list(community1, community2);

        when(communityRepository.findAll()).thenReturn(communities);
        when(communityMapper.toCommunityDTO(community1)).thenReturn(getCommunityDTO(1));
        when(communityMapper.toCommunityDTO(community2)).thenReturn(getCommunityDTO(2));

        List<CommunityDTO> allCommunities = communityService.getAllCommunities();

        assertEquals(2, allCommunities.size());
    }

    @Test
    public void testAddCommunity_happyFlow_expectSavedCommunity() {
        Community community = getCommunity(COMMUNITY_ID, new HashSet<>());
        AddCommunityRequest addCommunityRequest = getAddCommunityRequest();
        when(communityMapper.toCommunity(addCommunityRequest)).thenReturn(community);
        when(communityMapper.toCommunityDTO(community)).thenReturn(getCommunityDTO(COMMUNITY_ID));
        when(userRepository.findById(CREATOR_ID)).thenReturn(Optional.ofNullable(User.builder().id(CREATOR_ID).build()));

        CommunityDTO communityDTO = communityService.addCommunity(addCommunityRequest);

        assertNotNull(communityDTO);
        verify(communityRepository).save(community);
        verify(userRepository).findById(CREATOR_ID);
    }

    @Test
    public void testAddCommunity_nullCreatorId_expectUnsavedCommunity() {
        Community community = getCommunity(COMMUNITY_ID, new HashSet<>());
        community.setCreatorId(null);
        AddCommunityRequest addCommunityRequest = getAddCommunityRequest();
        when(communityMapper.toCommunity(addCommunityRequest)).thenReturn(community);
        when(communityMapper.toCommunityDTO(community)).thenReturn(getCommunityDTO(COMMUNITY_ID));

        communityService.addCommunity(addCommunityRequest);

        verify(userRepository).findById(null);
        verifyNoInteractions(communityRepository);
    }

    @Test
    public void testGetCommunityRides_multipleRidesExists_expectRides() {
        int numOfCommunities = 3;
        Community community = getCommunity(COMMUNITY_ID, new HashSet<>());
        Set<Ride> rides = getCommunityRides(community, numOfCommunities);
        mockRideMapperForRides(rides);
        community.setRides(rides);
        when(communityRepository.findById(COMMUNITY_ID)).thenReturn(Optional.of(community));

        List<RideDTO> communityRides = communityService.getRidesOfCommunity(COMMUNITY_ID);

        assertEquals(numOfCommunities, communityRides.size());
        for (RideDTO rideDTO : communityRides) {
            assertEquals(COMMUNITY_ID, rideDTO.getCommunityId());
        }
    }

    @Test
    public void testGetCommunityRides_noRidesExists_expectServiceToReturnEmptyResult() {
        int numOfCommunities = 0;
        Community community = getCommunity(COMMUNITY_ID, new HashSet<>());
        Set<Ride> rides = getCommunityRides(community, numOfCommunities);
        mockRideMapperForRides(rides);
        community.setRides(rides);
        when(communityRepository.findById(COMMUNITY_ID)).thenReturn(Optional.of(community));

        List<RideDTO> communityRides = communityService.getRidesOfCommunity(COMMUNITY_ID);

        assertEquals(numOfCommunities, communityRides.size());
    }

    private void mockRideMapperForRides(Set<Ride> rides) {
        for (Ride ride : rides) {
            when(rideMapper.toRideDto(ride)).thenReturn(RideDTO.builder()
                    .id(ride.getId())
                    .communityId(ride.getCommunity().getId())
                    .build());
        }
    }

    private Set<Ride> getCommunityRides(Community community, int numOfRides) {
        Set<Ride> rides = new HashSet<>();
        for (int i = 0; i < numOfRides; i++) {
            Ride ride = Ride.builder()
                    .id(i)
                    .community(community)
                    .build();

            rides.add(ride);
        }
        return rides;
    }

    private static Community getCommunity(int communityId, Set<Ride> rides) {
        return Community.builder()
                .id(communityId)
                .creatorId(CREATOR_ID)
                .rides(rides)
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