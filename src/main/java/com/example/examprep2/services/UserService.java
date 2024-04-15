package com.example.examprep2.services;

import com.example.examprep2.models.LoggedUser;
import com.example.examprep2.models.dtoS.binding.UserLoginDTO;
import com.example.examprep2.models.dtoS.binding.UserRegisterDTO;
import com.example.examprep2.models.entities.Offer;
import com.example.examprep2.models.entities.User;
import com.example.examprep2.models.models.OfferModel;
import com.example.examprep2.models.models.UserModel;
import com.example.examprep2.repositories.OfferRepository;
import com.example.examprep2.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, OfferRepository offerRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser (UserRegisterDTO userRegisterDTO){
        userRepository.save(modelMapper.map(userRegisterDTO, User.class));
    }

    public void loginUser (UserLoginDTO userLoginDTO){

        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElseThrow();

        loggedUser.setId(user.getId())
                .setUsername(user.getUsername());

    }

    public void logoutUser (){
        this.loggedUser.setId(null)
                .setUsername(null);
    }

    public User findUserById (Long id){
        return this.userRepository.findById(id).orElseThrow();
    }

    public void saveUserChanges (User user){
        this.userRepository.saveAndFlush(user);
    }

    public List<OfferModel> findALlOffersOfLoggedUser (){

        User user = userRepository.findById(loggedUser.getId()).orElseThrow();

        return user.getOffers()
                .stream()
                .map(offer -> modelMapper.map(offer, OfferModel.class))
                .collect(Collectors.toList());
    }

    public List<OfferModel> findAllBoughtOffersOfLoggedUser (){

        User user = userRepository.findById(loggedUser.getId()).orElseThrow();

        return user.getBoughtOffers()
                .stream()
                .map(offer -> modelMapper.map(offer, OfferModel.class))
                .collect(Collectors.toList());
    }

    public List<UserModel> findAllUserModelsNotLoggedUser (){

        return userRepository.findAllByIdNot(loggedUser.getId())
                .stream()
                .map(user -> modelMapper.map(user, UserModel.class))
                .collect(Collectors.toList());
    }

    public void buyItem (Long sellerId, Long offerId){

        User seller = userRepository.findById(sellerId).orElseThrow();
        User user = userRepository.findById(loggedUser.getId()).orElseThrow();

        Offer offer = seller.getOffers().stream().filter(offer1 -> offer1.getId().equals(offerId)).findFirst().orElseThrow();

        seller.getOffers().remove(offer);
        user.getBoughtOffers().add(offer);

        userRepository.saveAndFlush(seller);
        userRepository.saveAndFlush(user);
    }

    public void removeItem (Long offerId){

        User user = userRepository.findById(loggedUser.getId()).orElseThrow();

        Offer offer = user.getOffers().stream().filter(offer1 -> offer1.getId().equals(offerId)).findFirst().orElseThrow();

        user.getOffers().remove(offer);

        userRepository.saveAndFlush(user);

        offerRepository.delete(offer);
    }
}


