package com.example.examprep2.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
public class Offer extends BaseEntity{

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Condition condition;
}
