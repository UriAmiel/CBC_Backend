package com.cbc.cbc.rides.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CommunityRideResponse {

    @JsonProperty("community_rides")
    List<RideDTO> communityRides;
}
