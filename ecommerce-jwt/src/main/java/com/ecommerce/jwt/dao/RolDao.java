package com.ecommerce.jwt.dao;

import com.ecommerce.jwt.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDao extends CrudRepository<Rol, String> {
}
