package com.ecommerce.jwt.service;

import com.ecommerce.jwt.dao.RolDao;
import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.Rol;
import com.ecommerce.jwt.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public void inicializarRolesUsuarios() {

        Rol adminRole = new Rol();
        adminRole.setRolNombre("Admin");
        adminRole.setRolDescripcion("Admin role");
        rolDao.save(adminRole);

        Rol userRole = new Rol();
        userRole.setRolNombre("Usuario");
        userRole.setRolDescripcion("Default role for newly created record");
        rolDao.save(userRole);

        Usuario adminUser = new Usuario();
        adminUser.setUsuario("admin123");
        adminUser.setPassword(obtenerContraseñaCodificada("admin@123"));
        adminUser.setNombre("admin");
        adminUser.setApellido("admin");
        Set<Rol> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);//este id se copia en la tabla usuario_rol
        usuarioDao.save(adminUser);

        Usuario user = new Usuario();
        user.setUsuario("raj123");
        user.setPassword(obtenerContraseñaCodificada("raj@123"));
        user.setNombre("raj");
        user.setApellido("sharma");
        Set<Rol> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);//este id se copia en la tabla usuario_rol
        usuarioDao.save(user);
    }*/

    public Usuario registrarNuevoUsuario(Usuario usuario){
        Rol rol = rolDao.findById("Usuario").get();

        Set<Rol> rolSet = new HashSet<>();
        rolSet.add(rol);
        usuario.setRole((rolSet));

        String password = obtenerContrasenaCodificada(usuario.getPassword());
        usuario.setPassword(password);

        return usuarioDao.save(usuario);
    }

    public String obtenerContrasenaCodificada(String password) {
        return passwordEncoder.encode(password);
    }
    /*public Usuario registrandoNuevoUsuario(Usuario usuario) {
        Rol role = rolDao.findById("Usuario").get();
        Set<Rol> userRoles = new HashSet<>();
        userRoles.add(role);
        usuario.setRole(userRoles);
        usuario.setPassword( obtenerContraseñaCodificada(usuario.getPassword()));

        return usuarioDao.save(usuario);
    }

    public String obtenerContraseñaCodificada(String password) {
        return passwordEncoder.encode(password);
    }*/
}
