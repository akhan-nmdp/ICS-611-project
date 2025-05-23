package com.baeldung.hibernate.pojo;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class EntityDescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Any
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "S", entity = Employee.class)
    @AnyDiscriminatorValue(discriminator = "I", entity = Phone.class)
    @AnyKeyJavaClass(Integer.class)
    @Column(name = "entity_type")
    @JoinColumn(name = "entity_id")
    private Serializable entity;

    public EntityDescription() {
    }

    public EntityDescription(String description, Serializable entity) {
        this.description = description;
        this.entity = entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Serializable getEntity() {
        return entity;
    }

    public void setEntity(Serializable entity) {
        this.entity = entity;
    }
}
