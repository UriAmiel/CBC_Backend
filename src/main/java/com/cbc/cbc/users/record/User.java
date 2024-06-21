package com.cbc.cbc.users.record;

import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.rides.record.Ride;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "user_communities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    @Builder.Default
    private Set<Community> communities = new HashSet<>();

    @OneToMany(mappedBy = "driver")
    @Builder.Default
    private Set<Ride> rides = new HashSet<>();

    public void addCommunity(Community community) {
        communities.add(community);
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }
}
