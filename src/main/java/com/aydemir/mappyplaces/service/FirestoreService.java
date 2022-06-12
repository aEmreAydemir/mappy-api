package com.aydemir.mappyplaces.service;

import com.aydemir.mappyplaces.model.SearchResult;
import com.aydemir.mappyplaces.util.Constants;
import com.google.cloud.firestore.FirestoreException;
import com.google.firebase.cloud.FirestoreClient;
import io.grpc.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@AllArgsConstructor
public class FirestoreService {

    public void saveSearchResult(String searchId, SearchResult searchResult) {
        FirestoreClient.getFirestore().collection(Constants.FireStoreDocument.SEARCH_DOC).document(searchId).set(searchResult);
    }

    public SearchResult getSearchResult(String searchId) {
        try {
            return FirestoreClient
                    .getFirestore()
                    .collection(Constants.FireStoreDocument.SEARCH_DOC)
                    .document(searchId)
                    .get()
                    .get()
                    .toObject(SearchResult.class);
        } catch (InterruptedException | ExecutionException e) {
            log.error("A problem occurred while getting the file from firestore for the search id: {}", searchId);
            throw FirestoreException.forInvalidArgument(e.getMessage(), Status.ABORTED);
        }
    }
}
