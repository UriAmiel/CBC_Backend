package com.cbc.cbc.communities.repository;

import com.cbc.cbc.communities.pojo.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
