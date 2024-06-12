package com.cbc.cbc.communities.service;

import com.cbc.cbc.communities.model.dto.AddCommunityRequest;
import com.cbc.cbc.communities.model.dto.CommunityDTO;
import com.cbc.cbc.communities.model.mapper.CommunityMapper;
import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.communities.repository.CommunityRepository;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private CommunityRepository communityRepository;
    private UserRepository userRepository;
    private CommunityMapper communityMapper;

    @Override
    public CommunityDTO addCommunity(AddCommunityRequest communityToAdd) {
        Community community = communityMapper.toCommunity(communityToAdd);
        addCreatorToCommunity(community);
        communityRepository.save(community);
        return communityMapper.toCommunityDTO(community);
    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        return communityRepository.findAll()
                .stream()
                .map(community -> communityMapper.toCommunityDTO(community))
                .toList();
    }

    private void addCreatorToCommunity(Community community) {
        if (community != null) {
            addUserToCommunity(community, community.getCreatorId());
        }
    }

    private void addUserToCommunity(Community community, Long userId) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user != null) {
            user.addCommunity(community);
            community.addMember(user);
        }
    }
}