package org.iesvdm.ventas.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ClienteDAOImpl implements ClienteDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Cliente cliente) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("cliente")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", cliente.getNombre())
                .addValue("apellido1", cliente.getApellido1())
                .addValue("apellido2", cliente.getApellido2())
                .addValue("ciudad", cliente.getCiudad())
                .addValue("categoria", cliente.getCategoria());
        jdbcTemplate.update("INSERT into cliente (nombre, apellido1, apellido2, ciudad, categoría) VALUES (?, ?, ?, ?, ?)", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoria());
        log.info("Insertados {} registros.", 1);

    }

    @Override
    public List<Cliente> getAll() {
         List<Cliente> clienteList = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoría"))
        );
        log.info("Devueltos {} registros.", clienteList.size());
        return clienteList;
    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente cli = jdbcTemplate
                .queryForObject(
                "SELECT * FROM cliente WHERE id = ?",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                            rs.getString("nombre"),
                                            rs.getString("apellido1"),
                                            rs.getString("apellido2"),
                                            rs.getString("ciudad"),
                                            rs.getInt("categoría")),id
                );
        if (cli != null){
            return Optional.of(cli);
        }
        else{
            log.info("Cliente no encontrado");
            return Optional.empty();
        }
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoría = ? WHERE id = ?", cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getCiudad(), cliente.getCategoria(), cliente.getId());
        log.info("Update de Cliente con {} registros actualizados.", rows);
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ? ", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
