package com.emilpiekos.usersmanager.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class GeolocationService {

    public Location getLocationCoordinates(String address) throws NullPointerException {
        String requestContent = String.format("{\"address\":\"%s\"}", address);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(requestContent, mediaType);
        Request request = new Request.Builder()
                .url("https://geocoding.openapi.it/geocode")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", "Bearer 670655f2771542781d098dd9")
                .build();

        String responseBody;
        try (Response requestResponse = client.newCall(request).execute()) {
            assert requestResponse.body() != null;
            responseBody = requestResponse.body().string();
            ObjectMapper mapper = new ObjectMapper();
            LocationResponse response = mapper.readValue(responseBody, LocationResponse.class);
            BigDecimal latitude = BigDecimal.valueOf(response.getElement().getLatitude());
            BigDecimal longitude = BigDecimal.valueOf(response.getElement().getLongitude());
            return new Location(latitude, longitude);
        } catch (IOException e) {
            return null;
        }

    }
}
