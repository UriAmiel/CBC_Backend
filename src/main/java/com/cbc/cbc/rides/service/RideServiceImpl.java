package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDto;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;
    private RideMapper rideMapper;

    @Override
    public RideDto addRide(AddRideRequest rideToAdd) {
        Ride addedRide = rideRepository.save(rideMapper.toRide(rideToAdd));
        return rideMapper.toRideDto(addedRide);
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
