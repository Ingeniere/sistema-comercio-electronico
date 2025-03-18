package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.model.Rol;
import com.ecommerce.jwt.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping({"/nuevoRol"})
    public Rol crearNuevoRol(@RequestBody Rol rol) {
        return rolService.creandoNuevoRol(rol);
    }
}
