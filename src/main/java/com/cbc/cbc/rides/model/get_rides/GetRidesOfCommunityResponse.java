package com.cbc.cbc.rides.model.get_rides;

import com.cbc.cbc.rides.pojo.CommunityRideResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetRidesOfCommunityResponse {

    @JsonProperty("community_rides")
    List<CommunityRideResponse> communityRideResponses;
}
