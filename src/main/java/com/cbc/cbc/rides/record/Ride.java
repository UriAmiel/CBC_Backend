package com.cbc.cbc.rides.record;

import com.cbc.cbc.communities.record.Community;
import com.cbc.cbc.users.record.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "rides")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String source;
    private String destination;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
}
