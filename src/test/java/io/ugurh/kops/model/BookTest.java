package io.ugurh.kops.model;

import io.ugurh.kops.KopsApplication;
import io.ugurh.kops.entity.Book;
import io.ugurh.kops.entity.Shipping;
import io.ugurh.kops.repository.BookRepository;
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
public class BookTest {

    @Autowired
    public BookRepository repo;

    @Test
    public void testCreate() {
        Book book1 = new Book();
        book1.setName("Java SE");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("US");
        book1.setShipping(shipping1);
        repo.save(book1);

        Book book2 = new Book();
        book2.setName("EJB 3.0");
        Shipping shipping2 = new Shipping();
        shipping2.setCity("CAN");
        book2.setShipping(shipping2);
        repo.save(book2);

        book1.setName("JEE");
        shipping1 = book1.getShipping();
        shipping1.setCity("UK");
        repo.save(book1);

        List<Book> books = repo.findAll().stream().toList();
        assertEquals(2L, books.size());

        repo.delete(book2);

        books = repo.findAll().stream().toList();
        assertEquals(1L, books.size());
    }

}
