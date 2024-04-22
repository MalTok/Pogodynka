package pl.mt.weatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CityWeatherDto {
    private BigDecimal lat;
    private BigDecimal lon;
    private String timezone;
    private Long timezoneOffset;
    private LocalDateTime dt;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private double temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double dewPoint;
    private double uvi;
    private int clouds;
    private int visibility;
    private double windSpeed;
    private int windDeg;
    private double windGust;
    private String description;
    private String imgUrl;
}
