package org.iesvdm.ventas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;


    public Cliente(int idCliente) {
        this.id = idCliente;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.ciudad = null;
        this.categoria = 0;
    }
}
