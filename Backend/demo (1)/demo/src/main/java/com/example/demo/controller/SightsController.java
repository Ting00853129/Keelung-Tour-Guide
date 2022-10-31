package com.example.demo.controller;


import com.example.demo.collection.Sight;
import com.example.demo.service.SightService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)

public class SightsController {
    @Autowired
    private SightService sightService;

    @GetMapping("/getAllSight")
    public Object[] getData() {
        return sightService.getAllSight();
    }

    @GetMapping("/getZoneSight?{zone}")
    public Object[] getZoneSight(@PathVariable String zone) {
        return sightService.getSightByZone(zone);
    }

    @GetMapping("/getSight?{name}")
    public Object[] getSight(@PathVariable String name) {
        return sightService.getSightBySightName(name);
    }

}
