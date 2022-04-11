package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.BookDto;
import io.ugurh.kops.dto.ShippingDto;
import io.ugurh.kops.entity.Book;
import io.ugurh.kops.entity.Shipping;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final ShippingMapper shippingMapper;

    public BookMapper(ShippingMapper shippingMapper) {
        this.shippingMapper = shippingMapper;
    }

    public Book mapDtoToEntity(BookDto bookDto) {
        Book book = new Book();
        if (null != bookDto.getId()) book.setId(bookDto.getId());
        if (null != bookDto.getName()) book.setName(bookDto.getName());

        Shipping shipping = shippingMapper.mapDtoToEntity(bookDto.getShipping());
        if (null != bookDto.getShipping()) book.setShipping(shipping);

        return book;
    }

    public BookDto mapEntityToDto(Book book) {
        BookDto bookDto = new BookDto();
        if (null != book.getId()) bookDto.setId(book.getId());
        if (null != book.getName()) bookDto.setName(book.getName());

        ShippingDto shippingDto = shippingMapper.mapEntityToDto(book.getShipping());
        if (null != book.getShipping()) bookDto.setShipping(shippingDto);

        return bookDto;
    }
}
