package com.starwiper.learnSpring5.model;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class Taco {
  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<String> ingredients;
}
