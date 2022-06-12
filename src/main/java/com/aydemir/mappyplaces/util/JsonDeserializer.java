package com.aydemir.mappyplaces.util;

import com.aydemir.mappyplaces.model.Place;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonDeserializer {

    public static List<Place> getPlacesFromJson(String jsonResult) {
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");

        List<Place> parsedPlaces = new ArrayList<>();

        for (Object object : results) {
            JsonObject jsonObject1 = (JsonObject) object;
            String name = jsonObject1.get("name").toString();
            JsonObject location = jsonObject1.get("geometry").getAsJsonObject().get("location").getAsJsonObject();
            Place place = new Place(name, Double.parseDouble(location.get("lng").toString()), Double.parseDouble(location.get("lat").toString()));
            parsedPlaces.add(place);
        }

        return parsedPlaces;
    }
}
