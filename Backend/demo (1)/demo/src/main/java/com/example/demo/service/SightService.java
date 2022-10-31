package com.example.demo.service;

import com.example.demo.collection.Sight;
import com.example.demo.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.example.demo.NotFoundException;

@Service
public class SightService {
    @Autowired
    private SightRepository repository;

    public Object[] getSightByZone(String zone) {
        return repository.findSightByZone(zone)
                .orElseThrow(() -> new NotFoundException("Can't find."));
    }

    public Object[] getSightBySightName(String name) {
        return repository.findSightBySightName(name)
                .orElseThrow(() -> new NotFoundException("Can't find."));
    }

    public Object[] getAllSight() {
        return repository.findAll().toArray();
    }

    public void saveAllSight(Sight[] sights) {
        for (Sight s : sights) {
            repository.insert(s);
        }
    }

    public void deleteAllSight() {
        repository.deleteAll();
    }
}
