package de.aittr.g_27_security.service;

import de.aittr.g_27_security.domain.Role;
import de.aittr.g_27_security.domain.User;
import de.aittr.g_27_security.domain.dto.UserRegistrationDto;
import de.aittr.g_27_security.repositories.RoleRepository;
import de.aittr.g_27_security.repositories.UserRepository;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.lang.Integer;

@Service
public class UserService implements UserDetailsService {

  private UserRepository repository;
  private BCryptPasswordEncoder encoder;
  private RoleRepository roleRepository;

  public UserService(UserRepository repository, BCryptPasswordEncoder encoder,
      RoleRepository roleRepository) {
    this.repository = repository;
    this.encoder = encoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found!");
    }
    return user;
  }

  public UserRegistrationDto registerNewUser(UserRegistrationDto registrationDto) {
    User newUserRegister = repository.findByUsername(registrationDto.getUsername());
    if (newUserRegister !=null){
      throw new RuntimeException("Пользователь с таким именем" +
          registrationDto.getUsername()+ "уже существует!");
    }
    User newUser = new User();
    newUser.setUsername(registrationDto.getUsername());
    newUser.setPassword(encoder.encode(registrationDto.getPassword()));
    Role userRole = roleRepository.findByName("ROLE_USER");
    newUser.setRoles(Collections.singleton(userRole));
    newUser = repository.save(newUser);

    registrationDto.setId(newUser.getId());
    registrationDto.setPassword("Password is hidden");

    return registrationDto;
  }

  public void promoteUserToAdmin(Integer userId) {
    User user = repository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Пользователь не найден!"));

    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
    user.getRoles().add(adminRole);

    repository.save(user);
  }
}
