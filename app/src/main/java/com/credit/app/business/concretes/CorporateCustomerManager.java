package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CorporateCustomerService;
import com.credit.app.business.constants.CustomerTypeEnum;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.requests.corporateCustomer.AddCorporateCustomerRequest;
import com.credit.app.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.credit.app.business.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import com.credit.app.core.utilities.business.BusinessRules;
import com.credit.app.core.utilities.mapper.MapperUtil;
import com.credit.app.core.utilities.results.ErrorResult;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.SuccessResult;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.core.utilities.results.dataResults.ErrorDataResult;
import com.credit.app.core.utilities.results.dataResults.SuccessDataResult;
import com.credit.app.dataAccess.abstracts.CorporateCustomerDao;
import com.credit.app.entities.concretes.CorporateCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomerDao corporateCustomerDao;

    @Override
    public DataResult<Collection<GetAllCorporateCustomerResponse>> getAll() {
        Collection<CorporateCustomer> customers = corporateCustomerDao.findAll();
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED,
                MapperUtil.mapAll(customers, GetAllCorporateCustomerResponse.class));
    }

    @Override
    public DataResult<CorporateCustomer> add(AddCorporateCustomerRequest addRequest) {
        Result result = BusinessRules.run(checkCustomerExists(addRequest.getTaxNumber()));
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        final CorporateCustomer customerToAdd = MapperUtil.map(addRequest, CorporateCustomer.class);
        customerToAdd.setCustomerType(CustomerTypeEnum.CORPORATE);
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.ADDED,
                corporateCustomerDao.save(customerToAdd));
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateRequest) {
        final CorporateCustomer customerToUpdate = MapperUtil.map(updateRequest, CorporateCustomer.class);
        customerToUpdate.setCustomerType(CustomerTypeEnum.CORPORATE);
        corporateCustomerDao.save(customerToUpdate);
        return new SuccessResult(Messages.CUSTOMER_MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(Long id) {
        corporateCustomerDao.deleteById(id);
        return new SuccessResult(Messages.CUSTOMER_MESSAGE + Messages.DELETED);
    }

    @Override
    public DataResult<CorporateCustomer> getByTaxNumber(String taxNumber) {
        Optional<CorporateCustomer> customer = corporateCustomerDao.getByTaxNumber(taxNumber);
        if (customer.isPresent()) {
            return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED, customer.get());
        }
        return new ErrorDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.NOT_FOUND, null);
    }

    // Business rules

    private Result checkCustomerExists(String taxNumber) {
        if (getByTaxNumber(taxNumber).isSuccess()) {
            return new ErrorResult(Messages.CORPORATE_EXISTS);
        }
        return new SuccessResult();
    }

    // Business rules end
}
