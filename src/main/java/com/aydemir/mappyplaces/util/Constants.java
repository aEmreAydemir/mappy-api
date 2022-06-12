package com.aydemir.mappyplaces.util;

public interface Constants {

    interface GoogleApiRequest {
        String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
        String NEARBY_SEARCH = "/nearbysearch/json";
        String API_KEY = "AIzaSyCOX_4uGRUN6cU-nGXvBc-ZoIO5JQw6kBI";
    }

    interface FireStoreDocument {
        String SEARCH_DOC = "searches";
    }
}
