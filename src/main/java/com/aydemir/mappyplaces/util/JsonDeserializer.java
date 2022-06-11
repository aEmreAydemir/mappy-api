package com.aydemir.mappyplaces.util;

import com.aydemir.mappyplaces.model.Place;
import com.google.gson.*;

import java.util.List;
import java.util.ArrayList;
public class JsonDeserializer {

    public static List<Place> getPlacesFromJson(String jsonResult) {
        Gson gson = new Gson();

        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();
      //  JsonElement resultElement = jsonObject.get("results");
        JsonArray results = jsonObject.getAsJsonArray("results");

        List<Place> parsedPlaces = new ArrayList<>();
        for (Object object: results) {
            JsonObject jsonObject1 = (JsonObject) object;
            String name = jsonObject1.get("name").toString();
            JsonObject location = jsonObject1.get("geometry").getAsJsonObject().get("location").getAsJsonObject();
            Place place = new Place(name,Double.parseDouble(location.get("lng").toString()),Double.parseDouble(location.get("lat").toString()));
            parsedPlaces.add(place);
           // System.out.println(place.getName());
            //System.out.println(location.toString());
        }

        return parsedPlaces;
    }
}
