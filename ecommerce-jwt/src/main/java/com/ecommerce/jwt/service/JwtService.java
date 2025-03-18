package com.ecommerce.jwt.service;

import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.JwtPedido;
import com.ecommerce.jwt.model.JwtRespuesta;
import com.ecommerce.jwt.model.Usuario;
import com.ecommerce.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtRespuesta crearJwtToken(JwtPedido jwtRequest) throws Exception {
        String userName = jwtRequest.getUsuario();
        String userPassword = jwtRequest.getPassword();
        autenticacion(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generarToken(userDetails);

        Usuario user = usuarioDao.findById(userName).get();
        return new JwtRespuesta(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsuario(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
        }
    }

    private Set getAuthority(Usuario user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolNombre()));
        });
        return authorities;
    }

    private void autenticacion(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESHABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIALES NO VÁLIDAS", e);
        }
    }
}
