package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDto;

public interface RideService {

    RideDto addRide(AddRideRequest rideToAdd);

    CommunityRideResponse getRidesOfCommunity(int communityId);
}
