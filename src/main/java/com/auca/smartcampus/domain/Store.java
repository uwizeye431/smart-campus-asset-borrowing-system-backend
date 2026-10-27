package com.auca.smartcampus.domain;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "store_number")
    private int storeNumber;

    @Column(name = "name")
    private String name;

    public UUID getId() 
    {
        return id;
    }
    public int getStoreNumber() {
        return storeNumber;
    }
    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
}
