package com.example.examprep2.services;

import com.example.examprep2.models.entities.Condition;
import com.example.examprep2.models.enums.ConditionEnum;
import com.example.examprep2.models.models.ConditionModel;
import com.example.examprep2.repositories.ConditionRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;
    private final ModelMapper modelMapper;

    public ConditionService(ConditionRepository conditionRepository, ModelMapper modelMapper) {
        this.conditionRepository = conditionRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct(){

        if (conditionRepository.count() == 0){

            List<ConditionModel> conditionModels = List.of(

                    ConditionModel.builder()
                            .conditionName(ConditionEnum.EXCELLENT)
                            .description("In perfect condition").build(),

                    ConditionModel.builder()
                            .conditionName(ConditionEnum.GOOD)
                            .description("Some signs of wear and tear or minor defects").build(),

                    ConditionModel.builder()
                            .conditionName(ConditionEnum.ACCEPTABLE)
                            .description("The item is fairly worn but continues to function properly").build());


            conditionRepository.saveAllAndFlush(conditionModels.stream()
                    .map(categoryModel -> modelMapper.map(categoryModel, Condition.class))
                    .toList());
        }
    }

    public ConditionModel findConditionModelByName (ConditionEnum conditionEnum){
        return modelMapper.map(conditionRepository.findConditionByName(conditionEnum).orElseThrow(), ConditionModel.class);
    }
}
