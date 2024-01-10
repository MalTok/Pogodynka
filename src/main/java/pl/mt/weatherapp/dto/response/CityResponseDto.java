package pl.mt.weatherapp.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CityResponseDto {
    private String name;
    private BigDecimal lat;
    private BigDecimal lon;
    private String country;
    private String state;
    private Map<String, String> localNames;
}
