package com.cbc.cbc.rides.model.dto.mapper;

import com.cbc.cbc.rides.model.dto.AddRideRequest;
import com.cbc.cbc.rides.model.dto.RideDTO;
import com.cbc.cbc.rides.record.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RideMapper {

    @Mapping(target = "driver.id", source = "driverId")
    @Mapping(target = "community.id", source = "communityId")
    Ride toRide(AddRideRequest addRideRequest);

    @Mapping(target = "communityId", source = "community.id")
    RideDTO toRideDto(Ride ride);
}
