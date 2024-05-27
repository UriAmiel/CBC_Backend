package com.cbc.cbc.rides.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RideDto {

    private int id;
    private String source;
    private String destination;
    private int communityId;
}
