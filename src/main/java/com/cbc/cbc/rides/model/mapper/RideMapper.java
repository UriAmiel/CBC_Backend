package com.cbc.cbc.rides.model.mapper;

import com.cbc.cbc.rides.model.add_ride.AddRideRequest;
import com.cbc.cbc.rides.model.add_ride.AddRideResponse;
import com.cbc.cbc.rides.pojo.RideDto;
import com.cbc.cbc.rides.pojo.Ride;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RideMapper {
    Ride toRide(AddRideRequest addRideRequest);
    AddRideResponse toAddRideResponse(Ride ride);
    RideDto toRideDto(Ride ride);
}
