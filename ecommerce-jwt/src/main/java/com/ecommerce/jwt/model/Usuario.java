package com.ecommerce.jwt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {


    @Id
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USUARIO_ROL", joinColumns = {@JoinColumn(name = "USUARIO_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROL_ID")})
    private Set<Rol> role;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRole() {
        return role;
    }

    public void setRole(Set<Rol> role) {
        this.role = role;
    }
}
