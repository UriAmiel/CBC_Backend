package com.cbc.cbc.communities.controller;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
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

    @PostMapping("/add")
    public CommunityDTO addCommunity(@RequestBody AddCommunityRequest communityToAdd) {
        return communityService.addCommunity(communityToAdd);
    }

    @GetMapping("/getAll")
    public List<CommunityDTO> getAllCommunities() {
        return communityService.getAllCommunities();
    }
}
