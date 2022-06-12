package com.aydemir.mappyplaces.client;

import com.aydemir.mappyplaces.util.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "google-api-request",url = Constants.GoogleApiRequest.PLACES_API_BASE)
public interface GoogleApiClient {

    @RequestMapping(method = RequestMethod.GET,
            value = Constants.GoogleApiRequest.NEARBY_SEARCH,
            consumes = "application/json"
    )
    ResponseEntity<String> getNearbyPlacesFromGoogle(@RequestParam("location") String location,@RequestParam("radius") Long radius, @RequestParam("key") String key);
}
