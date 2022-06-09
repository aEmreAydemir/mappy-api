package com.aydemir.mappyplaces.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

@Service
public class MapService {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/nearbysearch";

    private static final String OUT_JSON = "/json";
    // KEY!
    private static final String API_KEY = "this is not my real key dont even bother";

    //todo fix this
    public String getPlaces(double latitude, double longitude, Long radius) throws IOException {

        StringBuilder sb = new StringBuilder(PLACES_API_BASE);
        sb.append(TYPE_SEARCH);
        sb.append(OUT_JSON);


        //sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
        sb.append("?location=" + String.valueOf(longitude) + "," + String.valueOf(latitude));
        sb.append("&radius=" + String.valueOf(radius));
        //sb.append("&type=restaurant");
        sb.append("&key=" + API_KEY);

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        //MediaType mediaType = MediaType.parse("text/plain");
        //RequestBody body = RequestBody.create(mediaType,"");
        Request request = new Request.Builder()
                .url(sb.toString())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(sb.toString());
        return response.body().string();
    }
}
