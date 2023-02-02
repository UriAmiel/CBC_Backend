package com.cbc.cbc.controller;

import com.cbc.cbc.pojo.Community;
import com.cbc.cbc.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@CrossOrigin
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/add")
    public String addCommunity(@RequestBody Community community) {
        communityService.saveCommunity(community);
        return String.format("Added new community. [community = %s]", community.toString());
    }

    @GetMapping("/getAll")
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }
}
