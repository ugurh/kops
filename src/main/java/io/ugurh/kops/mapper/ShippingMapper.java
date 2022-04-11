package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.ShippingDto;
import io.ugurh.kops.entity.Shipping;
import org.springframework.stereotype.Component;

@Component
public class ShippingMapper {

    public Shipping mapDtoToEntity(ShippingDto shippingDto) {
        Shipping shipping = new Shipping();
        if (null != shippingDto.getId()) shipping.setId(shippingDto.getId());
        if (null != shippingDto.getCity()) shipping.setCity(shippingDto.getCity());

        return shipping;
    }

    public ShippingDto mapEntityToDto(Shipping shipping) {
        ShippingDto shippingDto = new ShippingDto();
        if (null != shipping.getId()) shippingDto.setId(shipping.getId());
        if (null != shipping.getCity()) shippingDto.setCity(shipping.getCity());

        return shippingDto;
    }
}
