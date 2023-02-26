package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.IndividualCustomerService;
import com.credit.app.business.constants.CustomerTypeEnum;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.core.utilities.business.BusinessRules;
import com.credit.app.core.utilities.mapper.MapperUtil;
import com.credit.app.core.utilities.results.ErrorResult;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.SuccessResult;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.core.utilities.results.dataResults.ErrorDataResult;
import com.credit.app.core.utilities.results.dataResults.SuccessDataResult;
import com.credit.app.dataAccess.abstracts.IndividualCustomerDao;
import com.credit.app.entities.concretes.IndividualCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;

    @Override
    public DataResult<Collection<GetAllIndividualCustomerResponse>> getAll() {
        Collection<IndividualCustomer> customers = individualCustomerDao.findAll();
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED,
                MapperUtil.mapAll(customers, GetAllIndividualCustomerResponse.class));
    }

    @Override
    public DataResult<IndividualCustomer> add(AddIndividualCustomerRequest addRequest) {

        Result result = BusinessRules.run(checkCustomerExists(addRequest.getNationalId()));
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        final IndividualCustomer customerToAdd = MapperUtil.map(addRequest, IndividualCustomer.class);
        customerToAdd.setCustomerType(CustomerTypeEnum.INDIVIDUAL);
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.ADDED,
                individualCustomerDao.save(customerToAdd));
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateRequest) {
        final IndividualCustomer customerToUpdate = MapperUtil.map(updateRequest, IndividualCustomer.class);
        customerToUpdate.setCustomerType(CustomerTypeEnum.INDIVIDUAL);
        individualCustomerDao.save(customerToUpdate);
        return new SuccessResult(Messages.CUSTOMER_MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(Long id) {
        individualCustomerDao.deleteById(id);
        return new SuccessResult(Messages.CUSTOMER_MESSAGE + Messages.DELETED);
    }

    @Override
    public DataResult<IndividualCustomer> getByNationalId(String nationalId) {
        Optional<IndividualCustomer> customer = individualCustomerDao.getByNationalId(nationalId);
        if (customer.isPresent()) {
            return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED, customer.get());
        }
        return new ErrorDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.NOT_FOUND, null);
    }

    // Business rules

    private Result checkCustomerExists(String nationalId) {
        if (getByNationalId(nationalId).isSuccess()) {
            return new ErrorResult(Messages.INDIVIDUAL_EXISTS);
        }
        return new SuccessResult();
    }

    // Business rules end
}
