package com.cbc.cbc.rides.controller;

import com.cbc.cbc.rides.model.add_ride.AddRideRequest;
import com.cbc.cbc.rides.model.add_ride.AddRideResponse;
import com.cbc.cbc.rides.model.get_rides.CommunityRideResponse;
import com.cbc.cbc.rides.model.mapper.RideMapper;
import com.cbc.cbc.rides.pojo.Ride;
import com.cbc.cbc.rides.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
@AllArgsConstructor
public class RideController {

    private RideService rideService;
    private RideMapper rideMapper;

    @GetMapping("/get/{communityId}")
    public CommunityRideResponse getRidesOfCommunity(@PathVariable int communityId) {
        return rideService.getRidesOfCommunity(communityId);
    }

    @PostMapping("/add")
    public ResponseEntity<AddRideResponse> addRideToCommunity(@RequestBody AddRideRequest addRideRequest) {
        Ride rideToAdd = rideMapper.toRide(addRideRequest);
        Ride savedRide = rideService.addRide(rideToAdd);
        AddRideResponse rideResponse = rideMapper.toAddRideResponse(savedRide);
        return ResponseEntity.ok(rideResponse);
    }
}
