package io.ugurh.kops.service;

import io.ugurh.kops.dto.BookDto;
import io.ugurh.kops.entity.Book;
import io.ugurh.kops.exceptions.ResourceNotFoundException;
import io.ugurh.kops.mapper.BookMapper;
import io.ugurh.kops.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> findAll() {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            bookDtoList.add(bookMapper.mapEntityToDto(book));
        }
        return bookDtoList;
    }

    public BookDto findById(Long bookId) {
        Book book = getBook(bookId);
        return bookMapper.mapEntityToDto(book);
    }

    public void create(BookDto bookDto) {
        Book book = bookMapper.mapDtoToEntity(bookDto);
        bookRepository.save(book);
    }

    public void remove(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
    }

    public void edit(BookDto bookDto) {
        bookRepository.save(bookMapper.mapDtoToEntity(bookDto));
    }

    private Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book can not found!"));
    }

}
