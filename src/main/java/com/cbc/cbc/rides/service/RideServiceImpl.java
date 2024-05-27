package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.get_rides.GetRidesOfCommunityResponse;
import com.cbc.cbc.rides.pojo.CommunityRideResponse;
import com.cbc.cbc.rides.pojo.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;

    @Override
    public Ride addRide(Ride rideToAdd) {
        return rideRepository.save(rideToAdd);
    }

    @Override
    public GetRidesOfCommunityResponse getRidesOfCommunity(int communityId) {
        List<CommunityRideResponse> communityRideResponses = rideRepository.findAll()
                .stream()
                .filter(ride -> ride.getCommunityId() == communityId)
                .map(ride -> new CommunityRideResponse(ride.getId(), ride.getSource(), ride.getDestination()))
                .toList();
        return new GetRidesOfCommunityResponse(communityRideResponses);
    }
}
