package com.example.examprep2.models.models;

import com.example.examprep2.models.entities.Condition;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferModel {

    private Long id;
    private String description;

    private BigDecimal price;

    private ConditionModel condition;
}
