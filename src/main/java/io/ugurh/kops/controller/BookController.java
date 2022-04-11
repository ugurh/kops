package io.ugurh.kops.controller;

import io.ugurh.kops.dto.BookDto;
import io.ugurh.kops.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public @ResponseBody
    List<BookDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{bookId}")
    public @ResponseBody
    BookDto findById(@PathVariable("bookId") Long bookId) {
        return service.findById(bookId);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public void create(@RequestBody BookDto book) {
        service.create(book);
    }

    @PostMapping(value = "/remove/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("bookId") Long bookId) {
        service.remove(bookId);
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public void edit(@RequestBody BookDto book) {
        service.edit(book);
    }
}
