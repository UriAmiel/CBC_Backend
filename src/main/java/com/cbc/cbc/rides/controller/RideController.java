package com.cbc.cbc.rides.controller;

import com.cbc.cbc.rides.model.get_rides.GetRidesOfCommunityResponse;
import com.cbc.cbc.rides.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
@AllArgsConstructor
public class RideController {

    private RideService rideService;

    @GetMapping(
            "/get/{communityId}")
    public GetRidesOfCommunityResponse getRidesOfCommunity(@PathVariable int communityId) {
        return rideService.getRidesOfCommunity(communityId);
    }
}
