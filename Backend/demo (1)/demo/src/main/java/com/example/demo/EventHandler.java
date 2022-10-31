package com.example.demo;

import com.example.demo.collection.Sight;
import com.example.demo.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SourceFilteringListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    @Autowired
    private SightService sightService;

    @EventListener(ContextStartedEvent.class)
    public void initDatebase(ContextStartedEvent e){
        System.out.println("initialing database");

        sightService.deleteAllSight();

        KeelungSightsCrawler crawler =new KeelungSightsCrawler();
        String[] allZone = crawler.getAllZone();
        for(String zone :allZone){
            Sight[] tmp = crawler.getItems(zone);
            System.out.println(zone);
            sightService.saveAllSight(tmp);
        }
        System.out.println("done");
    }
}
