package com.example.examprep2.web;

import com.example.examprep2.models.LoggedUser;
import com.example.examprep2.models.models.OfferModel;
import com.example.examprep2.models.models.UserModel;
import com.example.examprep2.services.OfferService;
import com.example.examprep2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{
    private final UserService userService;
    private final LoggedUser loggedUser;

    public HomeController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }


    @GetMapping
    public ModelAndView getIndex (){

        if (loggedUser.getId() == null){
            return super.view("index");
        }

        return getHome(new ModelAndView());
    }

    @GetMapping("/home")
    public ModelAndView getHome (ModelAndView modelAndView){

        if (loggedUser.getId() == null){
            return super.redirect("/");
        }

        List<UserModel> userModels = userService.findAllUserModelsNotLoggedUser();
        List<OfferModel> aLlOffersOfLoggedUser = userService.findALlOffersOfLoggedUser();
        List<OfferModel> allBoughtOffersOfLoggedUser = userService.findAllBoughtOffersOfLoggedUser();

        int offersCount = userModels.stream()
                .mapToInt(userModel -> userModel.getOffers().size())
                .sum();

        modelAndView.addObject("aLlOffersOfLoggedUser", aLlOffersOfLoggedUser);
        modelAndView.addObject("allBoughtOffersOfLoggedUser", allBoughtOffersOfLoggedUser);

        modelAndView.addObject("userModels", userModels);
        modelAndView.addObject("offersCount", offersCount);




        return super.view("home", modelAndView);

    }

//    @ModelAttribute
//    public List<OfferModel> initOffersThatDontBelongToLoggedUser (){
//        return new ArrayList<>();
//    }
//
//    @ModelAttribute
//    public List<OfferModel> initALlOffersOfLoggedUser (){
//        return new ArrayList<>();
//    }
//
//    @ModelAttribute
//    public List<OfferModel> initAllBoughtOffersOfLoggedUser (){
//        return new ArrayList<>();
//    }
//
//    @ModelAttribute
//    public List<UserModel> initUserModels (){
//        return new ArrayList<>();
//    }


}
