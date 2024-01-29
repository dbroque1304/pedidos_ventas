package org.iesvdm.ventas.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {
    private int id;

    @NotBlank(message = "{co_val_blank_nombre}")
    @Size(max = 30, message = "{co_val_max_nombre}")
    private String nombre;

    @NotBlank(message = "{co_val_blank_apellido1}")
    @Size(max = 30, message = "{co_val_max_apellido1}")
    private String apellido1;
    private String apellido2;

    @DecimalMin(value = "0.276", message = "{co_val_com}")
    @DecimalMax(value = "0.946", message = "{co_val_com}")
    private BigDecimal comision;

    public Comercial(int idComercial) {
        this.id = idComercial;
    }
}