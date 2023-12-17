package com.cbc.cbc.rides.model.get_rides;

import com.cbc.cbc.rides.pojo.CommunityRideResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetRidesOfCommunityResponse {

    List<CommunityRideResponse> communityRideResponses;
}
