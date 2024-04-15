package com.example.examprep2.models.dtoS.binding;

import com.example.examprep2.models.enums.ConditionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferAddDTO {

    @Size (min = 2, max = 50)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private ConditionEnum condition;
}
