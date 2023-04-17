package com.mursey.account.service;

import com.mursey.account.dto.CustomerDto;
import com.mursey.account.dto.converter.CustomerDtoConverter;
import com.mursey.account.exception.CustomerNotFoundException;
import com.mursey.account.model.Customer;
import com.mursey.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    private CustomerService service;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, converter);
    }
    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = service.findCustomerById("id");

        assertEquals(result,customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, ()-> service.findCustomerById("id"));
    }

    @Test
    public void testGetByCustomerId_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id","name","surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id","name","surname",Set.of());


        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("id");

        assertEquals(result,customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerById("id"));

        Mockito.verifyNoInteractions(converter);
    }

}