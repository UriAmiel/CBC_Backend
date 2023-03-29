package com.cbc.cbc.communities.pojo;

import com.cbc.cbc.userRegistration.pojo.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "communities", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Transient
    private List<User> users;
}
