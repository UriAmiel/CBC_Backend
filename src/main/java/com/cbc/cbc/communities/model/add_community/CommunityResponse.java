package com.cbc.cbc.communities.model.add_community;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommunityResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}
