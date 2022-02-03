package com.crm.agile.monkeys.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private String photoUrl;
}
