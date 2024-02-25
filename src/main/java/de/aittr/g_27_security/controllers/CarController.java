package de.aittr.g_27_security.controllers;

import de.aittr.g_27_security.domain.Car;
import de.aittr.g_27_security.service.CarService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

  private CarService service;

  public CarController(CarService service) {
    this.service = service;
  }

  // Доступ для всех, в том числе незарегистрированных пользователей

  @GetMapping("/all")
  public List<Car> getAll() {
    return service.getAll();
  }

  // Доступ только для зарегистрированных пользователей
  @GetMapping("/by_id/{id}")
  public Car getById(@PathVariable int id) {
    return service.getById(id);
  }

  // Доступ только для админов
  @PostMapping("/save")
  public Car save(@RequestBody Car car) {
    return service.save(car);
  }
}