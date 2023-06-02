package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "residence")
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nbPersonne")
    private int nbPerson;

    @NotNull(message = "The type field cannot be null")
    @NotBlank(message = "The type field cannot be empty")
    @Column(name = "type")
    private String type;

    @Column(name = "principale")
    private boolean principal = false;

    @NotNull(message = "The address field cannot be null")
    @NotBlank(message = "The address field cannot be empty")
    @Column(name = "adresse")
    private String address;

    @NotNull(message = "The postalCode field cannot be null")
    @NotBlank(message = "The postalCode field cannot be empty")
    @Column(name = "codePostal")
    private String postalCode;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy="residence", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tank> tanks;

    @OneToMany(mappedBy="residence", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Weather> weathers;

    public Residence(){}
    public Residence(Long id, int nbPerson, @NotNull(message = "The type field cannot be null") String type, boolean principal, @NotNull(message = "The address field cannot be null") String address, @NotNull(message = "The postalCode field cannot be null") String postalCode, double lat, double lon) {
        this.id = id;
        this.nbPerson = nbPerson;
        this.type = type;
        this.principal = principal;
        this.address = address;
        this.postalCode = postalCode;
        this.lat = lat;
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tank> getTanks() {
        return tanks;
    }


    public List<Weather> getWeathers() {
        return weathers;
    }

}
