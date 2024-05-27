package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.get_rides.GetRidesOfCommunityResponse;
import com.cbc.cbc.rides.pojo.Ride;

public interface RideService {

    Ride addRide(Ride rideToAdd);

    GetRidesOfCommunityResponse getRidesOfCommunity(int communityId);
}
