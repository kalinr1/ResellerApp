package com.example.examprep2.models.entities;

import com.example.examprep2.models.enums.ConditionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@NoArgsConstructor
public class Condition extends BaseEntity{

    @Enumerated(EnumType.ORDINAL)
    @Column (unique = true, nullable = false)
    private ConditionEnum name;

    @Column (nullable = false)
    private String description;

}
