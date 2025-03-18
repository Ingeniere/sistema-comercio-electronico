package com.ecommerce.jwt.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Rol {


    @Id
    private String rolNombre;
    private String rolDescripcion;


    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }
}
