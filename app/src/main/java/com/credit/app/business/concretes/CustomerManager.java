package com.credit.app.business.concretes;

import java.util.Collection;
import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.responses.customer.GetAllCustomerResponse;
import com.credit.app.business.responses.customer.GetByIdCustomerResponse;
import com.credit.app.core.utilities.mapper.MapperUtil;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.core.utilities.results.dataResults.SuccessDataResult;
import com.credit.app.dataAccess.abstracts.CustomerDao;
import com.credit.app.entities.concretes.Customer;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;

    @Override
    public DataResult<Collection<GetAllCustomerResponse>> getAll() {
        Collection<Customer> customers = customerDao.findAll();
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED,
                MapperUtil.mapAll(customers, GetAllCustomerResponse.class));
    }

    @Override
    public DataResult<GetByIdCustomerResponse> getById(Long id) {
        final Customer customer = customerDao.findById(id).orElseThrow();
        return new SuccessDataResult<>(Messages.CUSTOMER_MESSAGE + Messages.LISTED,
                MapperUtil.map(customer, GetByIdCustomerResponse.class));
    }

}
