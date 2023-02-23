package com.credit.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.credit.app.entities.concretes.Credit;

public interface CreditDao extends JpaRepository<Credit, Long> {

}
