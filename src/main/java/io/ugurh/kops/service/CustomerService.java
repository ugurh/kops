package io.ugurh.kops.service;

import io.ugurh.kops.dto.CustomerDto;
import io.ugurh.kops.entity.Customer;
import io.ugurh.kops.mapper.CustomerMapper;
import io.ugurh.kops.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> findAll() {

        List<CustomerDto> customerDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            customerDtos.add(customerMapper.mapEntityToDto(customer));
        }

        return customerDtos;
    }

    public void create(CustomerDto customerDto) {
        Customer customer = customerMapper.mapDtoToEntity(customerDto);
        customerRepository.save(customer);
    }
}
