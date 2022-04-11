package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.CustomerDto;
import io.ugurh.kops.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

    Customer mapDtoToEntity(CustomerDto customerDto);
    CustomerDto mapEntityToDto(Customer customer);

}
