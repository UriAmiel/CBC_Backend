package com.cbc.cbc.communities.record;

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

    @ManyToMany(mappedBy = "communities")
    private Set<User> members = new HashSet<>();
}
