package pl.mt.weatherapp.dto.mapper;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mt.weatherapp.Weather;
import pl.mt.weatherapp.dto.CityWeatherDto;
import pl.mt.weatherapp.dto.response.CityWeatherResponseDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CityWeatherMapper {

    public CityWeatherDto mapResponseToDisplayDto(CityWeatherResponseDto cityWeatherResponseDto) {
        Long dt = cityWeatherResponseDto.getCurrent().getDt();
        Long sunrise = cityWeatherResponseDto.getCurrent().getSunrise();
        Long sunset = cityWeatherResponseDto.getCurrent().getSunset();

        return new CityWeatherDto(
                cityWeatherResponseDto.getLat(),
                cityWeatherResponseDto.getLon(),
                cityWeatherResponseDto.getTimezone(),
                cityWeatherResponseDto.getTimezoneOffset(),
                convert(dt),
                convert(sunrise),
                convert(sunset),
                cityWeatherResponseDto.getCurrent().getTemp(),
                cityWeatherResponseDto.getCurrent().getFeelsLike(),
                cityWeatherResponseDto.getCurrent().getPressure(),
                cityWeatherResponseDto.getCurrent().getHumidity(),
                cityWeatherResponseDto.getCurrent().getDewPoint(),
                cityWeatherResponseDto.getCurrent().getUvi(),
                cityWeatherResponseDto.getCurrent().getClouds(),
                cityWeatherResponseDto.getCurrent().getVisibility(),
                cityWeatherResponseDto.getCurrent().getWindSpeed(),
                cityWeatherResponseDto.getCurrent().getWindDeg(),
                cityWeatherResponseDto.getCurrent().getWindGust(),
                getCurrentWeatherDescription(cityWeatherResponseDto),
                getCurrentWeatherIconUrl(cityWeatherResponseDto)
        );
    }

    private String getCurrentWeatherDescription(CityWeatherResponseDto cityWeatherResponseDto) {
        return Arrays.stream(cityWeatherResponseDto.getCurrent().getWeather())
                .map(Weather::getDescription)
                .collect(Collectors.joining());
    }

    private String getCurrentWeatherIconUrl(CityWeatherResponseDto cityWeatherResponseDto) {
        String icon = Arrays.stream(cityWeatherResponseDto.getCurrent().getWeather())
                .map(Weather::getIcon)
                .collect(Collectors.joining());
        return UriComponentsBuilder.fromUriString("https://openweathermap.org/img/wn/{icon}.png")
                .buildAndExpand(icon)
                .toUriString();
    }

    private LocalDateTime convert(long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
