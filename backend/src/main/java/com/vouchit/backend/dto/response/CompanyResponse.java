package com.vouchit.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CompanyResponse {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;


}
