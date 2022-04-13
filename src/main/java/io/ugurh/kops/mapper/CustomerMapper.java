package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.CustomerDto;
import io.ugurh.kops.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapDtoToEntity(CustomerDto customerDto);

    CustomerDto mapEntityToDto(Customer customer);

}
