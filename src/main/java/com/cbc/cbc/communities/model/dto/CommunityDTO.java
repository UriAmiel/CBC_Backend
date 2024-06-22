package com.cbc.cbc.communities.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommunityDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("creatorId")
    private Long creatorId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("membersSize")
    private Long membersSize;
}
