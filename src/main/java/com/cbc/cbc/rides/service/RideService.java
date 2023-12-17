package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.get_rides.GetRidesOfCommunityResponse;
import com.cbc.cbc.rides.model.save_ride.SaveRideRequest;
import com.cbc.cbc.rides.model.save_ride.SaveRideResponse;

public interface RideService {

    SaveRideResponse saveRide(SaveRideRequest saveRideRequest);

    GetRidesOfCommunityResponse getRidesOfCommunity(int communityId);
}
