package io.ugurh.kops.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private ShippingDto shipping;

}
