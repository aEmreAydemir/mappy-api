package com.aydemir.mappyplaces.controller;

import com.aydemir.mappyplaces.model.Place;
import com.aydemir.mappyplaces.service.MapService;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


@CrossOrigin(origins = "https://mappy-places-web-njm47btaua-ew.a.run.app/", maxAge = 3600)
@RestController
@RequestMapping("api/map")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/places")
    public List<Place> getPlaces(@RequestParam double longitude, @RequestParam double latitude, @RequestParam Long radius) throws IOException, ExecutionException, InterruptedException {
        return  mapService.getPlaces(longitude,latitude,radius);
    }

    @GetMapping("/key")
    public String getGoogleMapsKey() {
        return mapService.getGoogleMapsKey();
    }
}
