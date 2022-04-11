package io.ugurh.kops.services;

import io.ugurh.kops.dto.CartDto;
import io.ugurh.kops.dto.CustomerDto;
import io.ugurh.kops.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testFindAll() {
        assertEquals(0L, customerService.findAll().size());
    }

    @Test
    void testCreate() {
        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setName("Alex");
        CartDto cartDto1 = new CartDto();
        cartDto1.setAmount(200.0);
        customerDto1.setCart(cartDto1);
        cartDto1.setCustomer(customerDto1);
        customerService.create(customerDto1);

        CustomerDto customerDto2 = new CustomerDto();
        customerDto2.setName("Fred");
        CartDto cartDto2 = new CartDto();
        cartDto2.setAmount(400.0);
        customerDto2.setCart(cartDto2);
        cartDto2.setCustomer(customerDto2);
        customerService.create(customerDto2);


        List<CustomerDto> x = customerService.findAll();

        assertEquals(2L, x.size());
    }
}
