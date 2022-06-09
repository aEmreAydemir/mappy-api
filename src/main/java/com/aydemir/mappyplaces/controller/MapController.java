package com.aydemir.mappyplaces.controller;

import com.aydemir.mappyplaces.service.MapService;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/map")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/places")
    public String getPlaces(@RequestParam double longitude, @RequestParam double latitude, @RequestParam Long radius) throws IOException {
        return  mapService.getPlaces(longitude,latitude,radius);
    }
}
