package com.cbc.cbc.rides.service;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDTO;

public interface RideService {

    RideDTO addRide(AddRideRequest rideToAdd);
}
