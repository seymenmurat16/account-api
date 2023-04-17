package com.mursey.account.dto.converter;

import com.mursey.account.dto.AccountCustomerDto;
import com.mursey.account.dto.CustomerDto;
import com.mursey.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.customerAccountDtoConverter = converter;
    }

    public AccountCustomerDto convertToAccountCustomerDto(Customer from){
        if (from == null){
            return new AccountCustomerDto("","","");
        }
        return new AccountCustomerDto(from.getId(),from.getName(),from.getSurname());
    }

    public CustomerDto convertToCustomerDto(Customer from){
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts().stream().map(customerAccountDtoConverter::convert).collect(Collectors.toSet())
        );
    }
}
