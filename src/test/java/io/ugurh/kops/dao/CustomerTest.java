package io.ugurh.kops.dao;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Cart;
import io.ugurh.kops.entity.Customer;
import io.ugurh.kops.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Transactional
public class CustomerTest {

    @Autowired
    private CustomerRepository repo;

    @Test
    public void testSave() {
        Customer customer1 = new Customer();
        customer1.setName("Alex");
        Cart cart1 = new Cart();
        cart1.setAmount(300.0);
        customer1.setCart(cart1);
        repo.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Fred");
        Cart cart2 = new Cart();
        cart2.setAmount(500.0);
        customer2.setCart(cart2);
        repo.save(customer2);

        assertEquals(2L, repo.findAll().size());
    }

    @Test
    public void testGetById() {
        Customer customer1 = new Customer();
        customer1.setName("Alex");
        Cart cart1 = new Cart();
        cart1.setAmount(300.0);
        customer1.setCart(cart1);
        repo.save(customer1);

        List<Customer> customers = repo.findAll();
        Customer tempCustomer = customers.get(0);
        Customer cust = repo.getById(tempCustomer.getId());
        assertEquals(tempCustomer.getName(), cust.getName());
    }

    @Test
    public void testDelete() {
        Customer customer1 = new Customer();
        customer1.setName("Alex");
        Cart cart1 = new Cart();
        cart1.setAmount(300.0);
        customer1.setCart(cart1);
        repo.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Fred");
        Cart cart2 = new Cart();
        cart2.setAmount(500.0);
        customer2.setCart(cart2);
        repo.save(customer2);

        assertEquals(2L, repo.findAll().size());

        repo.delete(customer2);

        assertEquals(1L, repo.findAll().size());
    }

    @Test
    public void testUpdate() {
        Customer customer1 = new Customer();
        customer1.setName("Alex");
        Cart cart1 = new Cart();
        cart1.setAmount(300.0);
        customer1.setCart(cart1);
        repo.save(customer1);

        assertEquals(1L, repo.findAll().size());

        List<Customer> customers = repo.findAll();
        Customer customer2 = customers.get(0);
        customer2.setName("Fred");
        repo.save(customer2);

        List<Customer> customers1 = repo.findAll();
        Customer customer3 = customers1.get(0);
        assertEquals("Fred", customer3.getName());
    }
}
