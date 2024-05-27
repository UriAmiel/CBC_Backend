package com.cbc.cbc.rides.model.mapper;

import com.cbc.cbc.rides.model.add_ride.AddRideRequest;
import com.cbc.cbc.rides.model.add_ride.AddRideResponse;
import com.cbc.cbc.rides.pojo.Ride;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideMapperTest {

    private static final int COMMUNITY_ID = 1;
    private static final int DRIVER_ID = 2;
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

        assertEquals(COMMUNITY_ID, ride.getCommunityId());
        assertEquals(DRIVER_ID, ride.getDriverId());
        assertEquals(DESTINATION, ride.getDestination());
        assertEquals(SOURCE, ride.getSource());
    }

    @Test
    public void testToAddRideResponse() {
        Ride ride = Ride.builder()
                .id(RIDE_ID)
                .build();

        AddRideResponse addRideResponse = rideMapper.toAddRideResponse(ride);

        assertEquals(RIDE_ID, addRideResponse.getId());
    }
}