package pl.mt.weatherapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityDto {
    @NotBlank(message = "Nazwa miasta nie może być pusta!")
    private String name;
}
