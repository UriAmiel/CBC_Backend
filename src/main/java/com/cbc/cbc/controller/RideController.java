package com.cbc.cbc.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
public class RideController {

    @GetMapping("/get/{communityId}")
    public String getRidesOfCommunity(@PathVariable int communityId) {
        return String.valueOf(communityId);
    }
}
