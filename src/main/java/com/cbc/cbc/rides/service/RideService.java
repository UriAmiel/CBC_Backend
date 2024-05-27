package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.get_rides.CommunityRideResponse;
import com.cbc.cbc.rides.pojo.Ride;

public interface RideService {

    Ride addRide(Ride rideToAdd);

    CommunityRideResponse getRidesOfCommunity(int communityId);
}
