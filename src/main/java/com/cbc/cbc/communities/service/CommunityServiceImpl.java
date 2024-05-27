package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.model.dto.mapper.CommunityMapper;
import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private CommunityRepository communityRepository;
    private CommunityMapper communityMapper;

    @Override
    public CommunityDTO addCommunity(AddCommunityRequest communityToAdd) {
        Community addedCommunity = communityRepository.save(communityMapper.toCommunity(communityToAdd));
        return communityMapper.toCommunityDTO(addedCommunity);
    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        return communityRepository.findAll()
                .stream()
                .map(community -> communityMapper.toCommunityDTO(community))
                .toList();
    }
}
