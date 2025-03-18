package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.model.JwtPedido;
import com.ecommerce.jwt.model.JwtRespuesta;
import com.ecommerce.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/autenticacion"})
    public JwtRespuesta crearJwtToken(@RequestBody JwtPedido jwtPedido) throws Exception {
        return jwtService.crearJwtToken(jwtPedido);
    }
}
