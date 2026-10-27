package com.auca.smartcampus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "asset_type")
public class AssetType 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "assetTypes")
    private List<Custodian> custodians = new ArrayList<>();

    public UUID getId()
     {
        return id;
      }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Custodian> getCustodians() {
        return custodians;
    }

    public void setCustodians(List<Custodian> custodians) {
        this.custodians = custodians;
    }
}
