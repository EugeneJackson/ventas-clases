package org.iesvdm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ComercialDAOImpl implements ComercialDAO {

    private static final RowMapper<Comercial> COMERCIAL_ROW_MAPPER = ComercialDAOImpl::mapComercial;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ComercialDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Comercial comercial) {
        String sql = "INSERT INTO comercial (nombre, apellido1, apellido2, comisión) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision());
    }

    @Override
    public List<Comercial> getAll() {
        return jdbcTemplate.query(
                "SELECT id, nombre, apellido1, apellido2, comisión FROM comercial",
                COMERCIAL_ROW_MAPPER);
    }

    @Override
    public Optional<Comercial> findById(Integer id) {
        List<Comercial> comerciales = jdbcTemplate.query(
                "SELECT id, nombre, apellido1, apellido2, comisión FROM comercial WHERE id = ?",
                COMERCIAL_ROW_MAPPER,
                id);
        return comerciales.stream().findFirst();
    }

    @Override
    public int update(Comercial comercial) {
        String sql = "UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
    }

    private static Comercial mapComercial(ResultSet resultSet, int rowNum) throws SQLException {
        float comision = resultSet.getFloat("comisión");
        Float nullableComision = resultSet.wasNull() ? null : comision;

        return new Comercial(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getString("apellido1"),
                resultSet.getString("apellido2"),
                nullableComision);
    }
}
