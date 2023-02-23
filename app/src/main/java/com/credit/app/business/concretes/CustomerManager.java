package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByNationalIdIndividualCustomerResponse;
import com.credit.app.core.utilities.mapper.MapperUtil;
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
public class CustomerManager implements CustomerService {

    private static final String MESSAGE = "Customer(s)";
    private IndividualCustomerDao individualCustomerDao;

    @Override
    public DataResult<Collection<GetAllIndividualCustomerResponse>> getAll() {
        Collection<IndividualCustomer> customers = individualCustomerDao.findAll();
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED,
                MapperUtil.mapAll(customers, GetAllIndividualCustomerResponse.class));
    }

    @Override
    public DataResult<GetByIdIndividualCustomerResponse> getById(Long id) {
        IndividualCustomer customer = individualCustomerDao.findById(id).orElseThrow();
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED,
                MapperUtil.map(customer, GetByIdIndividualCustomerResponse.class));
    }

    @Override
    public Result add(AddIndividualCustomerRequest addRequest) {
        IndividualCustomer customerToAdd = MapperUtil.map(addRequest, IndividualCustomer.class);
        individualCustomerDao.save(customerToAdd);
        return new SuccessResult(MESSAGE + Messages.ADDED);
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateRequest) {
        IndividualCustomer customerToUpdate = MapperUtil.map(updateRequest, IndividualCustomer.class);
        individualCustomerDao.save(customerToUpdate);
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(Long id) {
        individualCustomerDao.deleteById(id);
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

    @Override
    public DataResult<GetByNationalIdIndividualCustomerResponse> getByNationalId(String nationalId) {
        Optional<IndividualCustomer> customer = individualCustomerDao.getByNationalId(nationalId);
        if (customer.isPresent()) {
            GetByNationalIdIndividualCustomerResponse response = MapperUtil.map(customer,
                    GetByNationalIdIndividualCustomerResponse.class);
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, response);
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

}
