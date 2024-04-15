package com.example.examprep2.models.models;

import com.example.examprep2.models.enums.ConditionEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionModel {

    private Long id;

    @NotNull
    private ConditionEnum conditionName;

    @NotNull
    private String description;
}
