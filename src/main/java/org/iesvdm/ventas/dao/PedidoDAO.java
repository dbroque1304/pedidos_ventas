package org.iesvdm.ventas.dao;

import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Comercial;
import org.iesvdm.ventas.domain.Pedido;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);

    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);
    public List<Cliente> getAllClientesByIdPedido(int pedidoId);

    public void update(Pedido pedido);

    public void delete(int id);

    public int total(int id);
    public double media(int id);
}
