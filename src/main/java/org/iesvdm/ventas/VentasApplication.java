package org.iesvdm.ventas;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas.dao.ClienteDAO;
import org.iesvdm.ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class VentasApplication implements CommandLineRunner {

    @Autowired
    ClienteDAO clienteDAO;

    public static void main(String[] args) {
        SpringApplication.run(VentasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("*******************************");
        log.info("*Prueba de arranque ClienteDAO*");
        log.info("*******************************");

        clienteDAO.getAll().forEach(c -> log.info("Cliente: {}", c));

        clienteDAO.getAll().forEach(c -> log.info("Cliente: {}", c));

        log.info("************************************");
        log.info("*FIN: Prueba de arranque ClienteDAO*");
        log.info("************************************");

    }
}
