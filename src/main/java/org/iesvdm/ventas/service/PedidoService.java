package org.iesvdm.ventas.service;

import org.iesvdm.ventas.dao.ClienteDAO;
import org.iesvdm.ventas.dao.PedidoDAO;
import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ComercialService comercialService;


    public List<Pedido> listAll() {

        return pedidoDAO.getAll();

    }

    public List<Pedido> getAllPedidosByIdComercial(int id){
        List <Pedido> pedidoList = pedidoDAO.getAll().stream()
                .filter(pedido -> pedido.getComercial().getId() == id)
                .toList();
        return  pedidoList;
    }

    public Pedido one(Integer id) {
        Optional<Pedido> optPed = pedidoDAO.find(id);
        if (optPed.isPresent())
            return optPed.get();
        else
            return null;
    }

    public void newPedido(Pedido pedido) {

        pedidoDAO.create(pedido);

    }

    public void replacePedido(Pedido pedido) {

        pedidoDAO.update(pedido);

    }

    public void deletePedido(int id) {

        pedidoDAO.delete(id);

    }

    public double calcularTotal(int id){
        return this.pedidoDAO.total(id);
    }

    public double calcularMedia(int id){
        return this.pedidoDAO.media(id);
    }

}
