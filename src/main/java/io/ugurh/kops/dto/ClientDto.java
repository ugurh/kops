package io.ugurh.kops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Serializable {
    private Integer id;
    private String name;
    private Set<AccountDto> accounts = new HashSet<>();
}
