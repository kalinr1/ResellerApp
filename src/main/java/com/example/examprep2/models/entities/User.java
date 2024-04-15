package com.example.examprep2.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column (unique = true, nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (unique = true, nullable = false)
    private String email;

    @OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Offer> offers;

    @OneToMany (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Offer> boughtOffers;
}
