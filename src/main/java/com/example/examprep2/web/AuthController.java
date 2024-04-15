package com.example.examprep2.web;

import com.example.examprep2.models.LoggedUser;
import com.example.examprep2.models.dtoS.binding.UserLoginDTO;
import com.example.examprep2.models.dtoS.binding.UserRegisterDTO;
import com.example.examprep2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController{

    private final UserService userService;
    private final LoggedUser loggedUser;

    public AuthController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public ModelAndView getLogin (ModelAndView modelAndView){

        if (loggedUser.getId() != null){
            return super.redirect("/home");
        }

        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin (@Validated UserLoginDTO userLoginDTO,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("login", modelAndView.addObject("userLoginDTO", userLoginDTO));
        }

        userService.loginUser(userLoginDTO);

        return super.redirect("/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegister (ModelAndView modelAndView){

        if (loggedUser.getId() != null){
            return super.redirect("/home");
        }

        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView postRegister (@Validated UserRegisterDTO userRegisterDTO,
                                      BindingResult bindingResult,
                                      ModelAndView modelAndView){

        if (bindingResult.hasErrors()) {
            return super.view("register", modelAndView.addObject("userRegisterDTO", userRegisterDTO));
        }

        this.userService.registerUser(userRegisterDTO);

        return super.redirect("/home");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(){
        userService.logoutUser();
        return super.redirect("/");
    }

    @ModelAttribute
    public UserLoginDTO initLoginDTO (){
        return new UserLoginDTO();
    }

    @ModelAttribute
    public UserRegisterDTO initRegisterDTO (){
        return new UserRegisterDTO();
    }

}
