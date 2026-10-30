package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Comercial;

public interface ComercialDAO {

    int create(Comercial comercial);

    List<Comercial> getAll();

    Optional<Comercial> findById(Integer id);

    int update(Comercial comercial);

    int delete(Integer id);
}
