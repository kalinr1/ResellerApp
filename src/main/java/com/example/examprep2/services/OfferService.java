package com.example.examprep2.services;

import com.example.examprep2.models.LoggedUser;
import com.example.examprep2.models.dtoS.binding.OfferAddDTO;
import com.example.examprep2.models.entities.Offer;
import com.example.examprep2.models.entities.User;
import com.example.examprep2.models.models.ConditionModel;
import com.example.examprep2.models.models.OfferModel;
import com.example.examprep2.repositories.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final UserService userService;
    private final ConditionService conditionService;

    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;


    public OfferService(UserService userService, ConditionService conditionService, OfferRepository offerRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userService = userService;
        this.conditionService = conditionService;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void addOffer (OfferAddDTO offerAddDTO){

        User userById = userService.findUserById(loggedUser.getId());
        ConditionModel conditionModel = conditionService.findConditionModelByName(offerAddDTO.getCondition());


        Offer offerToSave = modelMapper.map(OfferModel.builder()
                .description(offerAddDTO.getDescription())
                .price(offerAddDTO.getPrice())
                .condition(conditionModel)
                .build(), Offer.class);

        userById.getOffers().add(offerToSave);

        userService.saveUserChanges(userById);


    }

    public List<OfferModel> findAllOffersThatDontBelongToLoggedUser (){

        List<OfferModel> allOffersOfLoggedUser = userService.findALlOffersOfLoggedUser();

        List<Offer> offerList1 = offerRepository.findAll();

        return offerList1.stream()
                .map(offer -> modelMapper.map(offer, OfferModel.class))
                .filter(offerModel -> allOffersOfLoggedUser.stream().noneMatch(offerModel1 -> offerModel1.getId().equals(offerModel.getId())))
                .collect(Collectors.toList());
    }
}

