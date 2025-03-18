package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.dao.RolDao;
import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.Rol;
import com.ecommerce.jwt.model.Usuario;
import com.ecommerce.jwt.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /*@PostConstruct
    public void iniciarRolesUsuarios() {
        usuarioService.inicializarRolesUsuarios();
    }*/

    @PostMapping({"/registrarUsuario"})
    public Usuario registrarNuevoUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarNuevoUsuario(usuario);
    }

    @GetMapping({"/entradaAdministrador"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "Esta URL solo es accesible para el administrador";
    }

    @GetMapping({"/entradaUsuario"})
    @PreAuthorize("hasRole('Usuario')")
    public String forUser(){
        return "Esta URL solo es accesible para el usuario";
    }
}
