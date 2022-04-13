package io.ugurh.kops.service;

import io.ugurh.kops.dto.CustomerDto;
import io.ugurh.kops.entity.Customer;
import io.ugurh.kops.entity.Product;
import io.ugurh.kops.exceptions.ResourceNotFoundException;
import io.ugurh.kops.mapper.CustomerMapper;
import io.ugurh.kops.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> findAll() {

        List<CustomerDto> customerDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            customerDtos.add(CustomerMapper.INSTANCE.mapEntityToDto(customer));
        }

        return customerDtos;
    }

    public void create(CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.mapDtoToEntity(customerDto);
        customerRepository.save(customer);
    }

    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));

        return CustomerMapper.INSTANCE.mapEntityToDto(customer);
    }

    public void remove(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        customerRepository.delete(customer);
    }
}
