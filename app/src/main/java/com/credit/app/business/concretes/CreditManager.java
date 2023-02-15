package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CreditService;
import com.credit.app.dataAccess.abstracts.CreditDao;
import com.credit.app.entities.concretes.Credit;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreditManager implements CreditService {

    private CreditDao creditDao;

    @Override
    public Collection<Credit> getAll() {
        return creditDao.findAll();
    }

    @Override
    public Credit getById(Long id) {
        Optional<Credit> result = creditDao.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public Credit add(Credit credit) {
        return creditDao.save(credit);
    }

    @Override
    public Credit update(Credit credit) {
        Optional<Credit> result = creditDao.findById(credit.getId());
        if (result.isPresent()) {
            return creditDao.save(credit);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Credit> result = creditDao.findById(id);
        if (result.isPresent()) {
            creditDao.delete(result.get());
        }
    }

}
