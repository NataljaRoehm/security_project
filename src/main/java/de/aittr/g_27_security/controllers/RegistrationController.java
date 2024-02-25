package de.aittr.g_27_security.controllers;

import de.aittr.g_27_security.domain.dto.UserRegistrationDto;
import de.aittr.g_27_security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

  private UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/newUser")
  public UserRegistrationDto registerUser(@RequestBody UserRegistrationDto registrationDto) {
    UserRegistrationDto user = userService.registerNewUser(registrationDto);
    return user;
  }
}