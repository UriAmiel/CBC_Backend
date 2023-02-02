package com.cbc.cbc.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private int id;
    private String name;
    @Transient
    private List<Community> communities;
}
