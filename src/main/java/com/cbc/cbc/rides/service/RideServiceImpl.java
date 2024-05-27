package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.get_rides.CommunityRideResponse;
import com.cbc.cbc.rides.model.mapper.RideMapper;
import com.cbc.cbc.rides.pojo.Ride;
import com.cbc.cbc.rides.pojo.RideDto;
import com.cbc.cbc.rides.repository.RideRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;
    private RideMapper rideMapper;

    @Override
    public Ride addRide(Ride rideToAdd) {
        return rideRepository.save(rideToAdd);
    }

    @Override
    public CommunityRideResponse getRidesOfCommunity(int communityId) {
        List<RideDto> communityRides = rideRepository.findAll()
                .stream()
                .filter(ride -> ride.getCommunityId() == communityId)
                .map(ride -> rideMapper.toRideDto(ride))
                .toList();
        return new CommunityRideResponse(communityRides);
    }
}
