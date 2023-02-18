package com.credit.app.entities.concretes;

import java.time.LocalDate;
import com.credit.app.entities.abstracts.AbstractCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "individual_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomer extends AbstractCustomer {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nationalId;

    @Column(nullable = false)
    private double income;

    @Column(nullable = false)
    private LocalDate birthDate;

}
