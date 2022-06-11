package com.aydemir.mappyplaces.service;

import com.aydemir.mappyplaces.model.Place;
import com.aydemir.mappyplaces.model.SearchResult;
import com.aydemir.mappyplaces.util.JsonDeserializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MapService {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/nearbysearch";

    private static final String OUT_JSON = "/json";
    // KEY!
    private static final String API_KEY = "fake";
    Firestore firestore;
    public List<Place> getPlaces(double latitude, double longitude, Long radius) throws IOException, ExecutionException, InterruptedException {

        firestore = FirestoreClient.getFirestore();
        String searchId = latitude+","+longitude+","+radius;

        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = firestore.collection("searches").document(searchId).get();
        SearchResult searchResult = documentSnapshotApiFuture.get().toObject(SearchResult.class);

        if (searchResult != null) {
            return searchResult.getPlaces();
        }

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
        String res = response.body().string();
        //System.out.println(res);
        List places = JsonDeserializer.getPlacesFromJson(res);

        SearchResult search = new SearchResult(places);

        firestore.collection("searches").document(searchId).set(search);

        return places;
    }
}
