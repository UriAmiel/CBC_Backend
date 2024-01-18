package com.cbc.cbc.communities.controller;

import com.cbc.cbc.communities.model.add_community.AddCommunityRequest;
import com.cbc.cbc.communities.model.add_community.CommunityResponse;
import com.cbc.cbc.communities.model.add_community.mapper.CommunityMapper;
import com.cbc.cbc.communities.pojo.Community;
import com.cbc.cbc.communities.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommunityResponse> addCommunity(@RequestBody AddCommunityRequest addCommunityRequest) {
        Community community = communityMapper.toCommunity(addCommunityRequest);
        Community savedCommunity = communityService.saveCommunity(community);
        CommunityResponse response = communityMapper.toCommunityResponse(savedCommunity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommunityResponse>> getAllCommunities() {
        List<Community> allCommunities = communityService.getAllCommunities();
        List<CommunityResponse> response = allCommunities.stream()
                .map(community -> communityMapper.toCommunityResponse(community))
                .toList();
        return ResponseEntity.ok(response);
    }
}
