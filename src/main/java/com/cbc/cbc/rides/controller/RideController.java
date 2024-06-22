package com.cbc.cbc.rides.controller;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
@AllArgsConstructor
public class RideController {

    private RideService rideService;

    @PostMapping("/add")
    public RideDTO addRideToCommunity(@RequestBody AddRideRequest rideToAdd) {
        return rideService.addRide(rideToAdd);
    }
}
