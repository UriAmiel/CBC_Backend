package com.cbc.cbc.rides.model.add_ride;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddRideRequest {

    private String source;
    private String destination;
    private int driverId;
    private int communityId;
}
