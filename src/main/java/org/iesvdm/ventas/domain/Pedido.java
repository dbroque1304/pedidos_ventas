package org.iesvdm.ventas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private Double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Cliente cliente;
    private Comercial comercial;
}
