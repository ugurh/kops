package io.ugurh.kops.dao;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Person;
import io.ugurh.kops.entity.Phone;
import io.ugurh.kops.exceptions.ResourceNotFoundException;
import io.ugurh.kops.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = KopsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Transactional
public class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testInsert() {
        Person p1 = new Person();
        p1.setName("Alex");
        List<Phone> phones = new ArrayList<>();
        Phone ph1 = new Phone();
        ph1.setNumber("7798989138");
        Phone ph2 = new Phone();
        ph2.setNumber("7798989169");
        phones.add(ph1);
        phones.add(ph2);
        p1.setPhones(phones);
        personRepository.save(p1);
        assertEquals(1L, personRepository.findAll().size());
    }

    @Test
    public void testGetAll() {
        assertEquals(0L, personRepository.findAll().size());
    }

    @Test
    public void testGetById() {
        Person p1 = new Person();
        p1.setName("Alex");
        List<Phone> phones = new ArrayList<>();
        Phone ph1 = new Phone();
        ph1.setNumber("7798989138");
        Phone ph2 = new Phone();
        ph2.setNumber("7798989169");
        phones.add(ph1);
        phones.add(ph2);
        p1.setPhones(phones);
        personRepository.save(p1);

        Person p2 = new Person();
        p2.setName("Fred");
        List<Phone> phones2 = new ArrayList<>();
        Phone phone1 = new Phone();
        phone1.setNumber("8836987");
        phones.add(phone1);
        p2.setPhones(phones2);
        personRepository.save(p2);
        assertEquals(2L, personRepository.findAll().size());

        List<Person> persons = personRepository.findAll();
        Person p3 = persons.get(1);

        Person p4 = personRepository.findById(p3.getId()).orElseThrow(() -> new ResourceNotFoundException(""));
        assertEquals("Fred", p4.getName());

    }

    @Test
    public void testDelete() {
        Person p1 = new Person();
        p1.setName("Alex");
        List<Phone> phones = new ArrayList<>();
        Phone ph1 = new Phone();
        ph1.setNumber("7798989138");
        Phone ph2 = new Phone();
        ph2.setNumber("7798989169");
        phones.add(ph1);
        phones.add(ph2);
        p1.setPhones(phones);
        personRepository.save(p1);

        Person p2 = new Person();
        p2.setName("Fred");
        List<Phone> phones2 = new ArrayList<>();
        Phone phone1 = new Phone();
        phone1.setNumber("8836987");
        phones.add(phone1);
        p2.setPhones(phones2);
        personRepository.save(p2);
        assertEquals(2L, personRepository.findAll().size());

        List<Person> persons = personRepository.findAll();
        Person p = persons.get(1);
        personRepository.delete(p);
        assertEquals(1L, personRepository.findAll().size());
    }

    @Test
    public void testUpdate() {
        Person p1 = new Person();
        p1.setName("Alex");
        List<Phone> phones = new ArrayList<>();
        Phone ph1 = new Phone();
        ph1.setNumber("7798989138");
        Phone ph2 = new Phone();
        ph2.setNumber("7798989169");
        phones.add(ph1);
        phones.add(ph2);
        p1.setPhones(phones);
        personRepository.save(p1);

        assertEquals(1L, personRepository.findAll().size());

        List<Person> persons = personRepository.findAll();
        Person p = persons.get(0);
        p.setName("Jhon");
        List<Phone> phones1 = new ArrayList<>();
        Phone ph3 = new Phone();
        ph1.setNumber("111111111");
        Phone ph4 = new Phone();
        ph2.setNumber("222222222");
        Phone ph5 = new Phone();
        ph2.setNumber("333333333");
        phones1.add(ph3);
        phones1.add(ph4);
        phones1.add(ph5);
        p.setPhones(phones1);
        personRepository.save(p);
        persons = personRepository.findAll();
        Person p2 = persons.get(0);
        assertEquals("Jhon", p2.getName());
        assertEquals(3L, p2.getPhones().size());
    }

}
