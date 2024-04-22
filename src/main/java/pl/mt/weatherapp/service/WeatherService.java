package pl.mt.weatherapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mt.weatherapp.dto.CityPreviewDto;
import pl.mt.weatherapp.dto.mapper.CityPreviewMapper;
import pl.mt.weatherapp.dto.mapper.CityWeatherMapper;
import pl.mt.weatherapp.dto.response.CityResponseDto;
import pl.mt.weatherapp.dto.CityWeatherDto;
import pl.mt.weatherapp.dto.response.CityWeatherResponseDto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    private static final String API_KEY = "cd688285683b69eca4db403088d2d1e1";
    private static final int LIMIT = 5;
    private static final String EXCLUDE = "minutely,hourly,daily,alerts";
    private static final String UNITS = "metric";
    private static final String LANG = "pl";

    private final CityWeatherMapper cityWeatherMapper;
    private final CityPreviewMapper cityPreviewMapper;

    public WeatherService(CityWeatherMapper cityWeatherMapper, CityPreviewMapper cityPreviewMapper) {
        this.cityWeatherMapper = cityWeatherMapper;
        this.cityPreviewMapper = cityPreviewMapper;
    }

    public Set<CityPreviewDto> getCities(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getGeocodingApiUrl(cityName);
        CityResponseDto[] cityResponseDto = restTemplate.getForObject(url, CityResponseDto[].class);

        if (cityResponseDto == null || cityResponseDto.length == 0) {
            return new HashSet<>();
        }
        return Arrays
                .stream(cityResponseDto)
                .map(cityPreviewMapper::mapResponseToPreviewDto)
                .collect(Collectors.toSet());
    }

    private String getGeocodingApiUrl(String cityName) {
        return UriComponentsBuilder.fromUriString("http://api.openweathermap.org/geo/1.0/direct")
                .queryParam("q", cityName)
                .queryParam("limit", LIMIT)
                .queryParam("appid", API_KEY)
                .build().toUriString();
    }

    public Optional<CityWeatherDto> getWeatherData(BigDecimal lat, BigDecimal lon) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getOneCallApiUrl(lat, lon);
        CityWeatherResponseDto cityWeatherResponseDto = restTemplate.getForObject(url, CityWeatherResponseDto.class);
        if (cityWeatherResponseDto != null) {
            return Optional.of(cityWeatherMapper.mapResponseToDisplayDto(cityWeatherResponseDto));
        }
        return Optional.empty();
    }

    private String getOneCallApiUrl(BigDecimal lat, BigDecimal lon) {
        return UriComponentsBuilder.fromUriString("https://api.openweathermap.org/data/3.0/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", EXCLUDE)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .queryParam("lang", LANG)
                .build().toUriString();
    }
}
