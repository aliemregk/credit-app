package com.credit.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.app.entities.concretes.Credit;

@Repository
public interface CreditDao extends JpaRepository<Credit, Long> {

}
