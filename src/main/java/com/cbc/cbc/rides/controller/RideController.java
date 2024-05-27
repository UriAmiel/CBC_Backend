package com.cbc.cbc.rides.controller;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.CommunityRideResponse;
import com.cbc.cbc.rides.model.dto.RideDto;
import com.cbc.cbc.rides.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
@AllArgsConstructor
public class RideController {

    private RideService rideService;

    @GetMapping("/get/{communityId}")
    public CommunityRideResponse getRidesOfCommunity(@PathVariable int communityId) {
        return rideService.getRidesOfCommunity(communityId);
    }

    @PostMapping("/add")
    public RideDto addRideToCommunity(@RequestBody AddRideRequest rideToAdd) {
        return rideService.addRide(rideToAdd);
    }
}
