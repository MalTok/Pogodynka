package pl.mt.weatherapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentWeather {
    private Long dt;

    private Long sunrise;

    private Long sunset;

    private double temp;

    @JsonProperty("feels_like")
    private double feelsLike;

    private int pressure;

    private int humidity;

    @JsonProperty("dew_point")
    private double dewPoint;

    private double uvi;

    private int clouds;

    private int visibility;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_deg")
    private int windDeg;

    @JsonProperty("wind_gust")
    private double windGust;

    private Weather[] weather;
}
