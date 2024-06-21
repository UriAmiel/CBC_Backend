package com.cbc.cbc.rides.service;

import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import com.sun.istack.NotNull;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RideServiceImplTest {
    private static final int COMMUNITY_1_ID = 1;
    private static final int COMMUNITY_2_ID = 2;
    private static final Ride RIDE1 = getRide(COMMUNITY_1_ID);
    private static final Ride RIDE2 = getRide(COMMUNITY_2_ID);
    private static final List<Ride> RIDES_LIST = Lists.list(RIDE1, RIDE2);
    private static final String SOURCE = "source";
    private static final String DESTINATION = "destination";
    private static final Long DRIVER_ID = 1L;
    private static final int COMMUNITY_ID = 1;

    private static final RideRepository rideRepository = Mockito.mock(RideRepository.class);
    private static final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private static final CommunityRepository communityRepository = Mockito.mock(CommunityRepository.class);
    private static final RideMapper mapper = Mockito.mock(RideMapper.class);
    private RideService rideService;

    @BeforeEach
    public void setUp() {
        rideService = new RideServiceImpl(rideRepository, userRepository, communityRepository, mapper);
    }

    @Test
    public void testGetRidesOfCommunity_communityExists_expectOneCommunity() {
        Mockito.when(rideRepository.findAll()).thenReturn(RIDES_LIST);
        Mockito.when(mapper.toRideDto(RIDE1)).thenReturn(RideDTO.builder().communityId(COMMUNITY_1_ID).build());

        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(COMMUNITY_1_ID);
        List<RideDTO> communityRides = ridesOfCommunity.getCommunityRides();

        assertEquals(1, communityRides.size());
        Assertions.assertNotNull(communityRides.get(0));
        assertEquals(COMMUNITY_1_ID, communityRides.get(0).getCommunityId());
    }

    @Test
    public void testGetRidesOfCommunity_communityDoesNotExists_expectNoCommunity() {
        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(1024);
        List<RideDTO> communityRides = ridesOfCommunity.getCommunityRides();

        assertEquals(0, communityRides.size());
    }

    @Test
    public void testAddRide_happyFlow_expectSavedRide() {
        AddRideRequest addRideRequest = getAddRideRequest();
        Ride rideToAdd = getRide(COMMUNITY_ID);
        User user = getUser();
        Community community = getCommunity();

        Mockito.when(userRepository.findById(DRIVER_ID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(communityRepository.findById(COMMUNITY_ID)).thenReturn(Optional.ofNullable(community));
        Mockito.when(mapper.toRide(addRideRequest)).thenReturn(rideToAdd);
        Mockito.when(mapper.toRideDto(rideToAdd)).thenReturn(getRideDTO());

        RideDTO rideDTO = rideService.addRide(addRideRequest);

        Mockito.verify(rideRepository).save(rideToAdd);
        assertEquals(SOURCE, rideDTO.getSource());
        assertEquals(DESTINATION, rideDTO.getDestination());
        assertEquals(COMMUNITY_ID, rideDTO.getCommunityId());
        assertNotNull(user);
        assertEquals(1, user.getRides().size());
        assertNotNull(community);
        assertEquals(1, community.getRides().size());
    }

    @Test
    public void testAddRide_noUser_expectNotToSaveRide_throwException() {
        AddRideRequest addRideRequest = getAddRideRequest();
        Ride rideToAdd = getRide(COMMUNITY_ID);

        Mockito.when(userRepository.findById(DRIVER_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> rideService.addRide(addRideRequest));
        Mockito.verify(rideRepository, Mockito.never()).save(rideToAdd);
    }

    @Test
    public void testAddRide_noCommunity_expectNotToSaveRide_throwException() {
        AddRideRequest addRideRequest = getAddRideRequest();
        Ride rideToAdd = getRide(COMMUNITY_ID);

        Mockito.when(communityRepository.findById(COMMUNITY_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> rideService.addRide(addRideRequest));
        Mockito.verify(rideRepository, Mockito.never()).save(rideToAdd);
    }

    private static RideDTO getRideDTO() {
        return RideDTO.builder()
                .source(SOURCE)
                .destination(DESTINATION)
                .communityId(COMMUNITY_ID)
                .build();
    }

    private static AddRideRequest getAddRideRequest() {
        return AddRideRequest.builder()
                .source(SOURCE)
                .destination(DESTINATION)
                .driverId(DRIVER_ID)
                .communityId(COMMUNITY_ID)
                .build();
    }

    @NotNull
    private static Ride getRide(int communityId) {
        return Ride.builder()
                .community(Community.builder().id(communityId).build())
                .build();
    }

    @NotNull
    private static User getUser() {
        return User.builder()
                .id(DRIVER_ID)
                .build();
    }

    @NotNull
    private static Community getCommunity() {
        return Community.builder()
                .id(COMMUNITY_ID)
                .build();
    }
}