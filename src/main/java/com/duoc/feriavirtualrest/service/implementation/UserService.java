package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.Rol;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.service.RolService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("rolService")
    private RolService rolService;

    @Override
    public UserDetails loadUserByUsername(String dato_usuario) throws UsernameNotFoundException {
        try {
            List<Usuario> usuario_encontrados = (List<Usuario>)usuarioService.SP_USUARIO_CONSULTAR_CORREO(dato_usuario);

            if (usuario_encontrados == null) {
                throw new UsernameNotFoundException(dato_usuario);
            }
            if( usuario_encontrados.size() == 0){
                throw new UsernameNotFoundException(dato_usuario);
            }

            if( usuario_encontrados.size() == 1 ){
                Usuario usuario_seleccionado = usuario_encontrados.get(0);
                Set<Rol> roles = new HashSet<>(rolService.SP_ROL_CONSULTAR_ID(usuario_seleccionado.getRol_id()));
                List<GrantedAuthority> authorities = buildAuthorities(roles);
                return buildUser(usuario_seleccionado, authorities);
            } else{
                throw new UsernameNotFoundException(dato_usuario);
            }


        }
        catch (Exception e){
            log.error("No encuentra el usuario.");
            return null;
        }
    }

    private List<GrantedAuthority> buildAuthorities(Set<Rol> roles) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for (Rol rol : roles) {
            auths.add(new SimpleGrantedAuthority(rol.getDescripcion()));
        }
        return new ArrayList<>(auths);
    }

    private User buildUser(Usuario usuario, List<GrantedAuthority> authorities) {
        return new User(usuario.getCorreo(), usuario.getContrasena(), (usuario.getHabilitado() == '1'), true, true, true, authorities);
    }
}
