package io.ugurh.kops.dto;

import io.ugurh.kops.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Integer id;
    private String name;
    private List<Phone> phones;
}
