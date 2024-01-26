package org.iesvdm.ventas.service;

import org.iesvdm.ventas.dao.ClienteDAO;
import org.iesvdm.ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;
    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }

    public Cliente one(Integer id) {
        Optional<Cliente> optCli = clienteDAO.find(id);
        if (optCli.isPresent())
            return optCli.get();
        else
            return null;
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);

    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);

    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);

    }

}
