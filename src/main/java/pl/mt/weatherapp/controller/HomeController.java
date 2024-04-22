package pl.mt.weatherapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mt.weatherapp.dto.CityDto;
import pl.mt.weatherapp.dto.CityPreviewDto;
import pl.mt.weatherapp.service.WeatherService;

import java.util.Set;

@Controller
public class HomeController {
    private final WeatherService weatherService;

    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("city", new CityDto());
        return "index";
    }

    @PostMapping("/search")
    public String search(@Valid @ModelAttribute("city") CityDto cityDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("city", cityDto);
            return "index";
        } else {
            Set<CityPreviewDto> cities = weatherService.getCities(cityDto.getName());
            if (cities.isEmpty()) {
                redirectAttributes.addFlashAttribute("emptyMessage", true);
            } else {
                redirectAttributes.addFlashAttribute("cities", cities);
            }
            redirectAttributes.addFlashAttribute("mode", "search");
            redirectAttributes.addFlashAttribute("city", cityDto);
        }
        return "redirect:/";
    }
}
