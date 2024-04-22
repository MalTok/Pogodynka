package pl.mt.weatherapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mt.weatherapp.dto.CityWeatherDto;
import pl.mt.weatherapp.service.WeatherService;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/result")
    public String showWeather(@RequestParam BigDecimal lat,
                              @RequestParam BigDecimal lon,
                              @RequestParam String name,
                              Model model) {
        Optional<CityWeatherDto> optionalCityWeatherDto = weatherService.getWeatherData(lat, lon);
        if (optionalCityWeatherDto.isPresent()) {
            CityWeatherDto cityWeatherDto = optionalCityWeatherDto.get();
            model.addAttribute("weather", cityWeatherDto);
            model.addAttribute("cityName", name);
        }
        return "weather-results";
    }
}
