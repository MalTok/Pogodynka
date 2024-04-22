package pl.mt.weatherapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.mt.weatherapp.CurrentWeather;

import java.math.BigDecimal;

@Data
public class CityWeatherResponseDto {
    private BigDecimal lat;
    private BigDecimal lon;
    private String timezone;
    @JsonProperty("timezone_offset")
    private Long timezoneOffset;
    private CurrentWeather current;
}
