package com.aydemir.mappyplaces.service;

import com.aydemir.mappyplaces.model.Place;
import com.aydemir.mappyplaces.model.SearchResult;
import com.aydemir.mappyplaces.util.Constants;
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

    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<DocumentSnapshot> documentSnapshotApiFuture;

    public List<Place> getPlaces(double latitude, double longitude, Long radius) throws IOException, ExecutionException, InterruptedException {

        String searchId = latitude+","+longitude+","+radius;

        documentSnapshotApiFuture = firestore.collection(Constants.FireStoreDocument.SEARCH_DOC).document(searchId).get();
        SearchResult searchResult = documentSnapshotApiFuture.get().toObject(SearchResult.class);

        if (searchResult != null) {
            return searchResult.getPlaces();
        }

        StringBuilder sb = new StringBuilder(Constants.GoogleApiRequest.PLACES_API_BASE);
        sb.append(Constants.GoogleApiRequest.NEARBY_SEARCH);
        sb.append(Constants.GoogleApiRequest.OUT_JSON);

        sb.append("?location=" + longitude + "," + latitude);
        sb.append("&radius=" + radius);
        //sb.append("&type=restaurant");
        sb.append("&key=" + Constants.GoogleApiRequest.API_KEY);

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(sb.toString())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String res = response.body().string();

        List places = JsonDeserializer.getPlacesFromJson(res);

        SearchResult search = new SearchResult(places);

        firestore.collection(Constants.FireStoreDocument.SEARCH_DOC).document(searchId).set(search);

        return places;
    }
}
