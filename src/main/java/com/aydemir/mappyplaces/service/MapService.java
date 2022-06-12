package com.aydemir.mappyplaces.service;

import com.aydemir.mappyplaces.client.GoogleApiClient;
import com.aydemir.mappyplaces.configuration.GoogleMapsConfiguration;
import com.aydemir.mappyplaces.model.Place;
import com.aydemir.mappyplaces.model.SearchResult;
import com.aydemir.mappyplaces.util.Constants;
import com.aydemir.mappyplaces.util.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {

    private final GoogleApiClient googleApiClient;
    private final FirestoreService firestoreService;
    private final GoogleMapsConfiguration googleMapsConfiguration;

    public List<Place> getPlaces(double latitude, double longitude, Long radius) {

        String searchId = latitude + "," + longitude + "," + radius;

        SearchResult searchResult = firestoreService.getSearchResult(searchId);

        if (searchResult != null) {
            return searchResult.getPlaces();
        }

        String location = longitude + "," + latitude;
        ResponseEntity<String> response = googleApiClient.getNearbyPlacesFromGoogle(location, radius, Constants.GoogleApiRequest.API_KEY);
        List<Place> places = JsonDeserializer.getPlacesFromJson(response.getBody());
        searchResult = new SearchResult(places);
        firestoreService.saveSearchResult(searchId, searchResult);
        return places;
    }

    public String getGoogleMapsKey() {
        return googleMapsConfiguration.getApiKey();
    }
}
