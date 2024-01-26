package org.iesvdm.ventas.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.ventas.domain.Cliente;
import org.iesvdm.ventas.domain.Comercial;
import org.iesvdm.ventas.domain.Pedido;
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
public class PedidoDAOImpl implements PedidoDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Pedido pedido) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("pedido")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("total", pedido.getTotal())
                .addValue("fecha", pedido.getFecha())
                .addValue("id_cliente", pedido.getCliente().getId())
                .addValue("id_comercial", pedido.getComercial().getId());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);
        pedido.setId(number.intValue());
        log.info("Insertados {} registros.", 1);
    }
    public List<Cliente> getAllClientesByIdPedido(int pedidoId) {

        List<Cliente> clienteList = this.jdbcTemplate.query("""
                select C.* from pedido P join cliente C on P.id_cliente = C.id  
                and P.id = ?
                """, (rs, rowNum) -> UtilDAO.newCliente(rs)
                , pedidoId);

        return clienteList;
    }


    @Override
    public List<Pedido> getAll() {
        List<Pedido> listPedido = this.jdbcTemplate.query("""
                SELECT * FROM  pedido P left join cliente C on  P.id_cliente = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                """, (rs, rowNum) -> UtilDAO.newPedido(rs)
        );
        return listPedido;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido ped = jdbcTemplate.queryForObject(
                "SELECT * FROM pedido WHERE id = ?",
                (rs, rowNum) -> new Pedido(rs.getInt("id"), rs.getDouble("total"), rs.getDate("fecha"), new Cliente(rs.getInt("id_cliente")), new Comercial(rs.getInt("id_comercial"))
                ), id);
        if (ped != null) {
            return Optional.of(ped);
        } else {
            log.info("Pedido no encontrado");
            return Optional.empty();
        }
    }

    @Override
    public void update(Pedido pedido) {
        int rows = jdbcTemplate.update("UPDATE pedido SET total = ?, fecha = ?, id_cliente = ?, id_comercial = ? WHERE id = ?", pedido.getTotal(),  pedido.getFecha(), pedido.getCliente().getId(),  pedido.getComercial().getId(), pedido.getId());
        log.info("Update de Pedido con {} registros actualizados.", rows);
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de pedido con 0 registros actualizados.");
        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }
}
