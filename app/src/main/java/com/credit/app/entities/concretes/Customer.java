package com.credit.app.entities.concretes;

import java.time.LocalDate;
import java.util.Set;

import com.credit.app.entities.abstracts.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernatelazyInitializer", "handle", "credits" })
public class Customer extends AbstractEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nationalID;

    @Column(nullable = false)
    private double income;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column
    private double guarantee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Credit> credits;
}
