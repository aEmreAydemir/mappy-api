package com.aydemir.mappyplaces.util;

public interface Constants {

    interface GoogleApiRequest {
        String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
        String NEARBY_SEARCH = "/nearbysearch/json";
    }

    interface FireStoreDocument {
        String SEARCH_DOC = "searches";
    }
}
