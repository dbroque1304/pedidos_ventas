package org.iesvdm.ventas.dto;

import org.iesvdm.ventas.domain.Comercial;

import java.util.List;

public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido1;
    private String ciudad;
    private int categoria;
    private List<Comercial> comercialList;

}
