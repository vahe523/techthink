package com.example.techthink.service.DTO;

public class AddressDTO {
    private String country;
    private String city;
    private String street;

    public AddressDTO(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public AddressDTO() {
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
