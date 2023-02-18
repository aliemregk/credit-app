package com.credit.app.entities.abstracts;

import java.util.Set;

import com.credit.app.entities.concretes.Credit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({ "hibernatelazyInitializer", "handle", "credits" })
public abstract class AbstractCustomer extends AbstractEntity {

    @Column(nullable = false)
    private String phone;

    @Column
    private double guarantee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Credit> credits;
}
