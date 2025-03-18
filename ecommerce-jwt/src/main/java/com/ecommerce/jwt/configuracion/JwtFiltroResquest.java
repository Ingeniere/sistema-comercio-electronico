package com.ecommerce.jwt.configuracion;

import com.ecommerce.jwt.service.JwtService;
import com.ecommerce.jwt.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFiltroResquest extends OncePerRequestFilter {

    public static String USUARIO_ACTUAL="";//Esto lo utilizamos para los pedidos en RealizarPedidoProductoService.java

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String encabezado=request.getHeader("Authorization");

        String usuario = null;
        String jwtToken = null;

        if (encabezado != null && encabezado.startsWith("Bearer ")) {
            jwtToken = encabezado.substring(7);
            try {
                usuario = jwtUtil.obtenerNombreDeUsuarioPorToken(jwtToken);
                USUARIO_ACTUAL = usuario;
            } catch (IllegalArgumentException e) {
                System.out.println("No se puede obtener el token JWT");
            } catch (ExpiredJwtException e) {
                System.out.println("El token JWT ha caducado");
            }
        } else {
            System.out.println("El token JWT no comienza con Bearer");
        }

        if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetalle = jwtService.loadUserByUsername(usuario);

            if (jwtUtil.validarToken(jwtToken, userDetalle)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetalle, null, userDetalle.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
