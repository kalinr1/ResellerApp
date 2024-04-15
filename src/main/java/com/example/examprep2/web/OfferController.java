package com.example.examprep2.web;

import com.example.examprep2.models.LoggedUser;
import com.example.examprep2.models.dtoS.binding.OfferAddDTO;
import com.example.examprep2.services.OfferService;
import com.example.examprep2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController extends BaseController{

    private final OfferService offerService;
    private final UserService userService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, UserService userService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public ModelAndView getAdd (ModelAndView modelAndView){

        if (loggedUser.getId() == null){
            return super.redirect("/auth/login");
        }

        return super.view("offer-add");
    }

    @PostMapping("/add")
    public ModelAndView postAdd (@Validated OfferAddDTO offerAddDTO,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView){


        if (bindingResult.hasErrors()) {
            return super.view("offer-add", modelAndView.addObject("offerAddDTO", offerAddDTO));
        }

        offerService.addOffer(offerAddDTO);

        return super.redirect("/home");
    }

    @GetMapping("/buy/{sellerId}/{id}")
    public ModelAndView buyOffer(@PathVariable Long sellerId, @PathVariable Long id,
                                 ModelAndView modelAndView){

        if (loggedUser.getId() == null){
            return super.redirect("/auth/login");
        }

        userService.buyItem(sellerId, id);

        return super.redirect("/home");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeOffer (@PathVariable Long id,
                                 ModelAndView modelAndView){

        if (loggedUser.getId() == null){
            return super.redirect("/auth/login");
        }

        userService.removeItem(id);

        return super.redirect("/home");
    }

    @ModelAttribute
    public OfferAddDTO initOfferAddDTO (){
        return new OfferAddDTO();
    }

}
