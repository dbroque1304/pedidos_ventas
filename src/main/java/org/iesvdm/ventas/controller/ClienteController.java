package org.iesvdm.ventas.controller;

import jakarta.validation.Valid;
import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String listar(Model model) {

        List<Cliente> listAllCli =  clienteService.listAll();
        model.addAttribute("listaClientes", listAllCli);

        return "clientes";

    }

    @GetMapping("/clientes/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "detalle-cliente";

    }

    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crear-cliente";

    }

    @PostMapping("/clientes/crear")
    public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "crear-cliente";
        }
        clienteService.newCliente(cliente);

        return "clientes";

    }

    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {


        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "editar-cliente";

    }


    @PostMapping("/clientes/editar/")
    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "editar-cliente";
        }
        clienteService.replaceCliente(cliente);

        return "clientes";
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }
}
