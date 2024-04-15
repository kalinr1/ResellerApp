package com.example.examprep2.models.models;

import com.example.examprep2.models.entities.Offer;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String username;

    private String password;

    private String email;

    private List<OfferModel> offers;

    private List<OfferModel> boughtOffers;
}
