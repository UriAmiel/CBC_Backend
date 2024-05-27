package com.cbc.cbc.rides.record;

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
    private int driverId;
    private int communityId;
}
