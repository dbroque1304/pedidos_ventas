package org.iesvdm.ventas.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int id;

    @NotBlank(message = "{c_val_blank_nombre}")
    @Size(max = 30, message = "{c_val_max_nombre}")
    private String nombre;

    @NotBlank(message = "{c_val_blank_apellido1}")
    @Size(max = 30, message = "{c_val_max_apellido1}")
    private String apellido1;

    private String apellido2;

    @NotBlank(message = "{c_val_blank_ciudad}")
    @Size(max = 30, message = "{c_val_max_ciudad}")
    private String ciudad;

    @Min(value = 100, message = "{c_val_minmax_categoria}")
    @Max(value = 1000, message = "{c_val_minmax_categoria}")
    private int categoria;

    @NotBlank(message = "{c_val_blank_email}")
    @Email(message = "{c_val_email}")
    private String email;

    public Cliente(int idCliente) {
        this.id = idCliente;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.ciudad = null;
        this.categoria = 0;
        this.email = null;
    }
}
