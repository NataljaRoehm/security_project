package de.aittr.g_27_security.controllers;


import de.aittr.g_27_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UserService userService;

  @PostMapping("/promote/{userId}")
  public ResponseEntity<String> promoteUserToAdmin(@PathVariable Integer userId) {
    userService.promoteUserToAdmin(userId);
    return ResponseEntity.ok("Пользователь успешно записан администратором!");
  }
}
