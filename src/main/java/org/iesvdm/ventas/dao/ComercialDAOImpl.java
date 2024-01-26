package org.iesvdm.ventas.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("comercial")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", comercial.getNombre())
                .addValue("apellido1", comercial.getApellido1())
                .addValue("apellido2", comercial.getApellido2())
                .addValue("comision", comercial.getComision());
        jdbcTemplate.update("INSERT into comercial (nombre, apellido1, apellido2, comisión) VALUES (?, ?, ?, ?)", comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());
        log.info("Insertados {} registros.", 1);
    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> comercialList = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getFloat("comisión"))
        );
        log.info("Devueltos {} registros.", comercialList.size());
        return comercialList;
    }

    @Override
    public Optional<Comercial> find(int id) {
        Comercial com = jdbcTemplate.queryForObject(
                "SELECT * FROM comercial WHERE id = ?",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getFloat("comisión")
                ), id);
        if (com != null) {
            return Optional.of(com);
        } else {
            log.info("Comercial no encontrado");
            return Optional.empty();
        }
    }

    @Override
    public void update(Comercial comercial) {
        int rows = jdbcTemplate.update("UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión = ? WHERE id = ?", comercial.getNombre(),  comercial.getApellido1(), comercial.getApellido2(),  comercial.getComision(),comercial.getId());
        log.info("Update de Cliente con {} registros actualizados.", rows);
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");
        log.info("Delete de Comercial con {} registros eliminados.", rows);
    }
}
