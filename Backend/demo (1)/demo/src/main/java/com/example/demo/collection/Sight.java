package com.example.demo.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Document(collection ="sight")

public class Sight {
    private String sightName;
    private String zone;
    private String category;
    private ArrayList<String> photoURL;
    private String description;
    private String address;
    private String addressLink;

    public Sight() {
        sightName = "";
        zone = "";
        category = "";
        photoURL = new ArrayList<>();
        address = "";
        description = "";
        addressLink = "";
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(ArrayList<String> photoURL) {
        this.photoURL = photoURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLink() {
        return addressLink;
    }

    public void setAddressLink(String addressLink) {
        this.addressLink = addressLink;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SightName: " + sightName + "\nZone: " + zone + "\nCategory: " + category + "\nPhotoURL:");
        for (int i = 0; i < photoURL.size(); ++i) {
            sb.append("\n" + photoURL.get(i));
        }
        sb.append("\nDescription: " + description + "\nAddress: " + address + "\nAddresslink:" + addressLink);
        return sb.toString();
    }
}
