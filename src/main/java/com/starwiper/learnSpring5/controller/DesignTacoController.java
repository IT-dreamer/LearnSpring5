package com.starwiper.learnSpring5.controller;

import com.starwiper.learnSpring5.model.Ingredient;
import com.starwiper.learnSpring5.model.Ingredient.Type;
import com.starwiper.learnSpring5.model.Taco;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Slf4j
@Controller
@RequestMapping(value = "/design")
public class DesignTacoController {

  @GetMapping
  public String showDesignForm(Model model) {
    System.out.println("Show design form");
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLOT", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    model.addAttribute("design", new Taco());
    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors) {
    if(errors.hasErrors()) {
      System.out.println("Error: " + errors.getAllErrors().stream().map(Object::toString).collect(Collectors.joining(", ")));
      return "design";
    }

    log.info("Process design: " + design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
  }
}
