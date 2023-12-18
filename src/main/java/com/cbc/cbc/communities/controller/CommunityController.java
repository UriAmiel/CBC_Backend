package com.cbc.cbc.communities.controller;

import com.cbc.cbc.communities.model.AddCommunityRequest;
import com.cbc.cbc.communities.model.mapper.AddCommunityResponse;
import com.cbc.cbc.communities.model.mapper.CommunityMapper;
import com.cbc.cbc.communities.pojo.Community;
import com.cbc.cbc.communities.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@CrossOrigin
@AllArgsConstructor
public class CommunityController {

    private CommunityService communityService;
    private CommunityMapper communityMapper;

    @PostMapping("/add")
    public AddCommunityResponse addCommunity(@RequestBody AddCommunityRequest addCommunityRequest) {
        Community community = communityMapper.toCommunity(addCommunityRequest);
        Community savedCommunity = communityService.saveCommunity(community);
        return communityMapper.toCommunityResponse(savedCommunity);
    }

    @GetMapping("/getAll")
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }
}
