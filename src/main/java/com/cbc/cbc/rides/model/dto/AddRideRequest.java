package com.cbc.cbc.rides.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddRideRequest {

    private String source;
    private String destination;
    private Long driverId;
    private int communityId;
}
