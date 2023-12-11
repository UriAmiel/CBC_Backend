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
    public Community addCommunity(@RequestBody Community community) {
        communityService.saveCommunity(community);
        return community;
    }

    @GetMapping("/getAll")
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }
}
