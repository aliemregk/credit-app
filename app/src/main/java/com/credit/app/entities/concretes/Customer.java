package com.credit.app.entities.concretes;

import java.util.Set;

import com.credit.app.business.constants.CustomerTypeEnum;
import com.credit.app.entities.abstracts.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends AbstractEntity {

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private double income;

    @Column
    private double guarantee;

    @Column(nullable = false, updatable = false)
    private CustomerTypeEnum customerType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Credit> credits;
}
