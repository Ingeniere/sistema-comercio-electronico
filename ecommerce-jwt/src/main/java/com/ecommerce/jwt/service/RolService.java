package com.ecommerce.jwt.service;

import com.ecommerce.jwt.dao.RolDao;
import com.ecommerce.jwt.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    private RolDao rolDao;

    public Rol creandoNuevoRol(Rol rol) {
        return rolDao.save(rol);
    }
}
