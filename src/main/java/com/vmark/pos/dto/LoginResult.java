package com.vmark.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {

    private Long empleadoId;
    private Long rolId;
    private Integer isValid;
    private String mensaje;
}
