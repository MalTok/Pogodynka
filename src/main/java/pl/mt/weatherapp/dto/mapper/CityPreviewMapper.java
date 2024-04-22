package pl.mt.weatherapp.dto.mapper;

import org.springframework.stereotype.Service;
import pl.mt.weatherapp.dto.CityPreviewDto;
import pl.mt.weatherapp.dto.response.CityResponseDto;

@Service
public class CityPreviewMapper {
    public CityPreviewDto mapResponseToPreviewDto(CityResponseDto cityResponseDto) {
        return new CityPreviewDto(
                cityResponseDto.getName(),
                cityResponseDto.getLat(),
                cityResponseDto.getLon(),
                cityResponseDto.getCountry(),
                cityResponseDto.getState()
        );
    }
}
