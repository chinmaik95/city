package com.example.cityapp.data;

public class City {
    String name;
    String country;
    String city;
    String imageUrl;

    public City(String name, String country, String city, String imageUrl) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return name+"\n"+country+"\n"+city+"\n"+imageUrl;
    }
}
