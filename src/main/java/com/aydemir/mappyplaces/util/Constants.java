package com.aydemir.mappyplaces.util;

public interface Constants {
    interface GoogleApiRequest {
        String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
        String TYPE_AUTOCOMPLETE = "/autocomplete";
        String TYPE_DETAILS = "/details";
        String NEARBY_SEARCH = "/nearbysearch";
        String OUT_JSON = "/json";
        String API_KEY = "fake";
    }

    interface FireStoreDocument {
        String SEARCH_DOC = "searches";
    }
}
