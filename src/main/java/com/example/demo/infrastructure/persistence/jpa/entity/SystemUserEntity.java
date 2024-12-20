package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "systemuser")
public class SystemUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    private String username;
    private String password;
}
