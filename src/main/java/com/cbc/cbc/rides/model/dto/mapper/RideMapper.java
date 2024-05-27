package com.cbc.cbc.rides.model.dto.mapper;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDto;
import com.cbc.cbc.rides.record.Ride;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RideMapper {
    Ride toRide(AddRideRequest addRideRequest);

    RideDto toRideDto(Ride ride);
}
