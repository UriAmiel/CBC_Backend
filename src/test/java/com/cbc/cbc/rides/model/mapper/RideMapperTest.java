package com.cbc.cbc.rides.model.mapper;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.model.dto.mapper.RideMapperImpl;
import com.cbc.cbc.rides.record.Ride;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RideMapperTest {

    private static final int COMMUNITY_ID = 1;
    private static final Long DRIVER_ID = 2L;
    private static final String DESTINATION = "destination";
    private static final String SOURCE = "source";
    private static final int RIDE_ID = 10;

    private RideMapper rideMapper;

    @BeforeEach
    public void setUp() {
        rideMapper = new RideMapperImpl();
    }

    @Test
    public void testToRide() {
        AddRideRequest addRideRequest = AddRideRequest.builder()
                .communityId(COMMUNITY_ID)
                .driverId(DRIVER_ID)
                .destination(DESTINATION)
                .source(SOURCE)
                .build();

        Ride ride = rideMapper.toRide(addRideRequest);

        assertEquals(COMMUNITY_ID, ride.getCommunity().getId());
        assertEquals(DRIVER_ID, ride.getDriver().getId());
        assertEquals(DESTINATION, ride.getDestination());
        assertEquals(SOURCE, ride.getSource());
    }

    @Test
    public void testToRideDto() {
        Ride ride = Ride.builder()
                .id(RIDE_ID)
                .build();

        RideDTO rideDto = rideMapper.toRideDto(ride);

        assertEquals(RIDE_ID, rideDto.getId());
    }
}