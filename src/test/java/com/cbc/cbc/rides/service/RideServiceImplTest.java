package com.cbc.cbc.rides.service;

import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import com.sun.istack.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RideServiceImplTest {
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
    public void testAddRide_happyFlow_expectSavedRide() {
        AddRideRequest addRideRequest = getAddRideRequest();
        Ride rideToAdd = getRide();
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
        Ride rideToAdd = getRide();

        Mockito.when(userRepository.findById(DRIVER_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> rideService.addRide(addRideRequest));
        Mockito.verify(rideRepository, Mockito.never()).save(rideToAdd);
    }

    @Test
    public void testAddRide_noCommunity_expectNotToSaveRide_throwException() {
        AddRideRequest addRideRequest = getAddRideRequest();
        Ride rideToAdd = getRide();

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
    private static Ride getRide() {
        return Ride.builder()
                .community(Community.builder().id(RideServiceImplTest.COMMUNITY_ID).build())
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