package com.cbc.cbc.rides.service;

import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.model.dto.mapper.RideMapper;
import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.rides.repository.RideRepository;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;
    private UserRepository userRepository;
    private CommunityRepository communityRepository;
    private RideMapper rideMapper;

    @Override
    public RideDTO addRide(AddRideRequest rideToAdd) {
        User driver = userRepository.findById(rideToAdd.getDriverId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find driver"));

        Community community = communityRepository.findById(rideToAdd.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find community"));

        Ride ride = rideMapper.toRide(rideToAdd);
        ride.setDriver(driver);
        ride.setCommunity(community);

        driver.addRide(ride);
        community.addRide(ride);

        rideRepository.save(ride);
        return rideMapper.toRideDto(ride);
    }

    @Override
    public CommunityRideResponse getRidesOfCommunity(int communityId) {
        List<RideDTO> communityRides = rideRepository.findAll()
                .stream()
                .filter(ride -> ride.getCommunity().getId() == communityId)
                .map(ride -> rideMapper.toRideDto(ride))
                .toList();
        return new CommunityRideResponse(communityRides);
    }
}
