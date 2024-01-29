package org.iesvdm.ventas.controller;

import jakarta.validation.Valid;
import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Comercial;
import org.iesvdm.ventas.domain.Pedido;
import org.iesvdm.ventas.service.ClienteService;
import org.iesvdm.ventas.service.ComercialService;
import org.iesvdm.ventas.service.PedidoService;
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
public class ComerciarController {
    @Autowired
    private ComercialService comercialService;
    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/comerciales")
    public String listar(Model model) {

        List<Comercial> listAllCom =  comercialService.listAll();
        model.addAttribute("listaComerciales", listAllCom);

        return "comerciales";

    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);
        List<Pedido> listaPedidos = pedidoService.getAllPedidosByIdComercial(id);
        model.addAttribute("listaPedidos", listaPedidos);
        double media = this.pedidoService.calcularMedia(id);
        double total = this.pedidoService.calcularTotal(id);
        model.addAttribute("media", media);
        model.addAttribute("total", total);
        return "detalle-comercial";

    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";

    }

    @PostMapping("/comerciales/crear")
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("comercial", comercial);
            return "crear-comercial";
        }
        comercialService.newComercial(comercial);

        return "comerciales" ;

    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";

    }


    @PostMapping("/comerciales/editar/")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("comercial", comercial);
            return "editar-comercial";
        }
        comercialService.replaceComercial(comercial);

        return "comerciales";
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }
}
