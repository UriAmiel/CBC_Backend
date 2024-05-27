package com.cbc.cbc.rides.controller;

import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.model.dto.RideDto;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.rides.service.RideService;
import com.cbc.cbc.rides.service.RideServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class RideControllerTest {


    private static final RideMapper MAPPER = Mockito.mock(RideMapper.class);
    private static final RideRepository REPOSITORY = Mockito.mock(RideRepository.class);
    private static final int COMMUNITY_1_ID = 1;
    private static final int COMMUNITY_2_ID = 2;
    private static final Ride RIDE1 = getRide(COMMUNITY_1_ID);
    private static final Ride RIDE2 = getRide(COMMUNITY_2_ID);
    private static final List<Ride> RIDES_LIST = Lists.list(RIDE1, RIDE2);
    private RideService rideService;

    @BeforeEach
    public void setUp() {
        Mockito.when(REPOSITORY.findAll()).thenReturn(RIDES_LIST);
        Mockito.when(MAPPER.toRideDto(RIDE1)).thenReturn(RideDto.builder().communityId(COMMUNITY_1_ID).build());
        rideService = new RideServiceImpl(REPOSITORY, MAPPER);
    }

    @Test
    public void testGetRidesOfCommunity_communityExists_expectOneCommunity() {
        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(COMMUNITY_1_ID);
        List<RideDto> communityRides = ridesOfCommunity.getCommunityRides();

        Assertions.assertEquals(1, communityRides.size());
        Assertions.assertNotNull(communityRides.get(0));
        Assertions.assertEquals(COMMUNITY_1_ID, communityRides.get(0).getCommunityId());
    }

    @Test
    public void testGetRidesOfCommunity_communityDoesNotExists_expectNoCommunity() {
        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(3);
        List<RideDto> communityRides = ridesOfCommunity.getCommunityRides();

        Assertions.assertEquals(0, communityRides.size());
    }

    private static Ride getRide(int communityId) {
        return Ride.builder()
                .communityId(communityId)
                .build();
    }
}