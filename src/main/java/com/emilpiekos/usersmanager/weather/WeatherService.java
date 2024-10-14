package com.emilpiekos.usersmanager.weather;

import com.emilpiekos.usersmanager.location.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Service
public class WeatherService {

    public Set<String> getWeatherParameters(Location location) throws NullPointerException {
        String latitude = location.getLatitude().toString();
        String longitude = location.getLongitude().toString();
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=temperature_2m_max,temperature_2m_min,precipitation_sum&forecast_days=1", latitude, longitude);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {

            ObjectMapper mapper;
            String json;
            try (Response response = client.newCall(request).execute()) {
                mapper = new ObjectMapper();
                json = Objects.requireNonNull(response.body()).string();
            }
            WeatherResponse weatherResponse = mapper.readValue(json, WeatherResponse.class);
            Double tempMin = weatherResponse.daily.temperature2mMin.get(0);
            String tempMinUnits = weatherResponse.daily_units.temperature2mMin;
            String maxTemperature = String.format("%s : %s%s", "Minimum temperature", tempMin, tempMinUnits);
            Double tempMax = weatherResponse.daily.temperature2mMax.get(0);
            String tempMaxUnits = weatherResponse.daily_units.temperature2mMax;
            String minTemperature = String.format("%s : %s%s", "Maximum temperature", tempMax, tempMaxUnits);
            Double precipitationAmount = weatherResponse.daily.precipitation_sum.get(0);
            String precipitationSumUnits = weatherResponse.daily_units.precipitation_sum;
            String precipitationSum = String.format("%s : %s%s", "Precipitation sum", precipitationAmount, precipitationSumUnits);

            Set<String> weatherParameters = new TreeSet<>();
            weatherParameters.add(minTemperature);
            weatherParameters.add(maxTemperature);
            weatherParameters.add(precipitationSum);
            return weatherParameters;

        } catch (IOException e) {
            return null;
        }

    }
}
