package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class RideServiceImplTest {
    private static final int COMMUNITY_1_ID = 1;
    private static final int COMMUNITY_2_ID = 2;
    private static final Ride RIDE1 = getRide(COMMUNITY_1_ID);
    private static final Ride RIDE2 = getRide(COMMUNITY_2_ID);
    private static final List<Ride> RIDES_LIST = Lists.list(RIDE1, RIDE2);
    private RideService rideService;

    @BeforeEach
    public void setUp() {
        RideRepository repository = Mockito.mock(RideRepository.class);
        RideMapper mapper = Mockito.mock(RideMapper.class);
        Mockito.when(repository.findAll()).thenReturn(RIDES_LIST);
        Mockito.when(mapper.toRideDto(RIDE1)).thenReturn(RideDTO.builder().communityId(COMMUNITY_1_ID).build());

        rideService = new RideServiceImpl(repository, mapper);
    }

    @Test
    public void testGetRidesOfCommunity_communityExists_expectOneCommunity() {
        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(COMMUNITY_1_ID);
        List<RideDTO> communityRides = ridesOfCommunity.getCommunityRides();

        Assertions.assertEquals(1, communityRides.size());
        Assertions.assertNotNull(communityRides.get(0));
        Assertions.assertEquals(COMMUNITY_1_ID, communityRides.get(0).getCommunityId());
    }

    @Test
    public void testGetRidesOfCommunity_communityDoesNotExists_expectNoCommunity() {
        CommunityRideResponse ridesOfCommunity = rideService.getRidesOfCommunity(1024);
        List<RideDTO> communityRides = ridesOfCommunity.getCommunityRides();

        Assertions.assertEquals(0, communityRides.size());
    }

    private static Ride getRide(int communityId) {
        return Ride.builder()
                .communityId(communityId)
                .build();
    }

}