package org.iesvdm.ventas.controller;

import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Comercial;
import org.iesvdm.ventas.domain.Pedido;
import org.iesvdm.ventas.service.ClienteService;
import org.iesvdm.ventas.service.ComercialService;
import org.iesvdm.ventas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ComercialService comercialService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/pedidos")
    public String listar(Model model) {

        List<Pedido> listAllPed =  pedidoService.listAll();
        model.addAttribute("listaPedidos", listAllPed);

        return "pedidos";

    }

    @GetMapping("/pedidos/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);
        Cliente cliente = clienteService.one(pedido.getCliente().getId());
        Comercial comercial = comercialService.one(pedido.getComercial().getId());
        model.addAttribute("cliente", cliente);
        model.addAttribute("comercial", comercial);

        return "detalle-pedido";

    }

    @GetMapping("/pedidos/crear")
    public String crear(Model model) {

        Pedido pedido = new Pedido();
        List<Cliente> listaClientes = clienteService.listAll();
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("pedido", pedido);

        return "crear-pedido";

    }

    @PostMapping("/pedidos/crear")
    public RedirectView submitCrear(@ModelAttribute("pedido") Pedido pedido) {

        pedidoService.newPedido(pedido);

        return new RedirectView("/pedidos") ;

    }

    @GetMapping("/pedidos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Pedido pedido = pedidoService.one(id);
        model.addAttribute("pedido", pedido);
        List<Cliente> listaClientes = clienteService.listAll();
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);
        model.addAttribute("listaClientes", listaClientes);

        return "editar-pedido";

    }


    @PostMapping("/pedidos/editar/")
    public RedirectView submitEditar(@ModelAttribute("pedido") Pedido pedido) {

        pedidoService.replacePedido(pedido);

        return new RedirectView("/pedidos");
    }

    @PostMapping("/pedidos/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        pedidoService.deletePedido(id);

        return new RedirectView("/pedidos");
    }
}
