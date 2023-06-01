package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "cuve")
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "masse")
    private double mass;

    @Column(name = "contenance")
    private double capacity;

    @NotNull(message = "The serialnumber field cannot be null")
    @NotBlank(message = "The serialnumber field cannot be empty")
    @Column(name = "numeroSerie")
    private String serialnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Residence residence;

    @OneToMany(mappedBy="tank", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TankData> tankData;

    public Tank(){}
    public Tank(Long id, double mass, double capacity, @NotNull(message = "The serialnumber field cannot be null") String serialnumber) {
        this.id = id;
        this.mass = mass;
        this.capacity = capacity;
        this.serialnumber = serialnumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Residence getResidence() {
        return residence;
    }


    public List<TankData> getTankData() {
        return tankData;
    }

}
