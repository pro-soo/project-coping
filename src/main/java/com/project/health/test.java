package com.project.health;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class test {

    @Id
    @Column(name = "test_id")
    private Long id;

    private String name;
}
