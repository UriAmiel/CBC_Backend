package com.cbc.cbc.communities.record;

import com.cbc.cbc.rides.record.Ride;
import com.cbc.cbc.users.record.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "communities")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long creatorId;
    @ManyToMany(mappedBy = "communities")
    @Builder.Default
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "community")
    @Builder.Default
    private Set<Ride> rides = new HashSet<>();

    public void addMember(User user) {
        members.add(user);
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }
}
